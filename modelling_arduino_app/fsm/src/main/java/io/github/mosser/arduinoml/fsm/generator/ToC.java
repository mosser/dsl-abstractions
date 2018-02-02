package io.github.mosser.arduinoml.fsm.generator;

import io.github.mosser.arduinoml.fsm.model.Actuator;
import io.github.mosser.arduinoml.fsm.model.App;
import io.github.mosser.arduinoml.fsm.model.State;
import io.github.mosser.arduinoml.fsm.model.Action;

public class ToC extends Visitor {

	public ToC() { this.code = new StringBuffer(); }

	private void c(String s) {
		code.append(String.format("%s\n",s));
	}


	@Override
	public void visit(App app) {
		c("// C code generated from an object model");
		c(String.format("// Application name: %s\n", app.getName()));
		c("#include <avr/io.h>");
		c("#include <util/delay.h>");
		c("#include <Arduino.h>");
		c("");
		c("void setup(){");
		for(Actuator a: app.getActuators()){
			a.accept(this);
		}
		c("}\n");

		for(State state: app.getStates()){
			c(String.format("void state_%s();", state.getName()));
		}
        c("\n");

		for(State state: app.getStates()){
			state.accept(this);
		}

		if (app.getInitial() != null) {
			c("int main(void) {");
			c("  setup();");
			c(String.format("  state_%s();", app.getInitial().getName()));
			c("  return 0;");
			c("}");
		}
	}

	@Override
	public void visit(Actuator actuator) {
	 	c(String.format("  pinMode(%d, OUTPUT); // %s [Actuator]", actuator.getPin(), actuator.getName()));
	}


	@Override
	public void visit(State state) {
		c(String.format("void state_%s() {",state.getName()));
		for(Action action: state.getActions()) {
			action.accept(this);
		}
		c("  _delay_ms(1000);");
		c(String.format("  state_%s();", state.getNext().getName()));
		c("}");
	}


	@Override
	public void visit(Action action) {
		c(String.format("  digitalWrite(%d,%s);",action.getActuator().getPin(),action.getValue()));
	}

}
