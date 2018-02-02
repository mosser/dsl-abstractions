package io.github.mosser.arduinoml.reactive.generator;

import io.github.mosser.arduinoml.reactive.model.*;
import io.github.mosser.arduinoml.reactive.model.actions.*;
import io.github.mosser.arduinoml.reactive.model.actions.expressions.*;

import java.util.HashMap;
import java.util.Map;

public class ToC extends Visitor {

    public ToC() { this.code = new StringBuffer(); }

	private void c(String s) {
		code.append(String.format("%s\n",s));
	}
    private void i(String s) {
        code.append(String.format("%s",s));
    }

	@Override public void visit(App app) {
		c("// C code generated from an object model");
		c(String.format("// Application name: %s\n", app.getName()));
		c("#include <avr/io.h>");
		c("#include <util/delay.h>");
		c("#include <Arduino.h>");
		c("");

		// Setting up actuators
		c("void setup(){");
		for(Actuator a: app.getActuators()){
			a.accept(this);
		}
		c("}\n");

        // Visit producers
        for(Producer p: app.getProducers())
            p.accept(this);

		// Visit consumers
        for(Consumer c: app.getConsumers())
            c.accept(this);

        // Generating the Main loop
        c("int main(void) {");
        c("  setup();");
        c("  while(1) {");
        for(Producer p: app.getProducers())
            c(String.format("    %s_producer();", p.getName()));
        for(Consumer c: app.getConsumers())
            c(String.format("    %s_consumer();", c.getName()));
        c(String.format("    _delay_ms(%d);", app.getPeriod()));
        c("  }");
        c("  return 0;");
        c("}");
	}


	@Override public void visit(Actuator actuator) {
		c(String.format("  pinMode(%d, OUTPUT); // %s [Actuator]", actuator.getPin(), actuator.getName()));
	}


    @Override public void visit(Producer producer) {
        c(String.format("bool %s_producer_MSG;", producer.getName()));
        c(String.format("void %s_producer() {", producer.getName()));
        c(String.format("  %s_producer_MSG = %s;", producer.getName(), producer.getValue()));
        c("}\n");
    }

	@Override
	public void visit(Consumer consumer) {
        for(Registry r: consumer.getMemory())
            r.accept(this);
        c(String.format("void %s_consumer() {", consumer.getName()));
        c(String.format("  bool received_message = %s_producer_MSG;",consumer.getTrigger().getName()));
        for(Action a: consumer.getBehavior())
            a.accept(this);
        c("}\n");
	}

	private Map<Registry, String> registries = new HashMap<>();

	@Override public void visit(Registry registry) {
	    String name = String.format("%s_consumer_%s", registry.getOwner().getName(),registry.getName());
	    registries.put(registry, name);
        c("bool " + name + ";");
	}

    @Override public void visit(Guard guard) {
        if(guard.getExpectation())
            i(String.format("  if(%s) ", registries.get(guard.getTarget())));
        else
            i(String.format("  if(!%s) ", registries.get(guard.getTarget())));

    }

	@Override public void visit(SetActuator setActuator) {
        boolean guarded = setActuator.getGuard().isPresent();
        if(guarded)
            setActuator.getGuard().get().accept(this);
        c(String.format("digitalWrite(%d,%s);",setActuator.getTarget().getPin(),setActuator.getValue()));
	}

	@Override public void visit(SetRegistry setRegistry) {
        if(setRegistry.getGuard().isPresent())
            setRegistry.getGuard().get().accept(this);
        i(String.format("  %s = ", registries.get(setRegistry.getTarget())));
        setRegistry.getExpression().accept(this);
        c(";");
	}


	@Override public void visit(Not not) {
        i("!");
        not.getE().accept(this);
	}

	@Override public void visit(ReadRegistry readRegistry) {
        i(registries.get(readRegistry.getTarget()));
	}
}
