package io.github.mosser.arduinoml.reactive.samples;

import io.github.mosser.arduinoml.reactive.generator.*;
import io.github.mosser.arduinoml.reactive.model.*;
import io.github.mosser.arduinoml.reactive.model.actions.*;
import io.github.mosser.arduinoml.reactive.model.actions.expressions.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.github.mosser.arduinoml.reactive.model.SIGNAL.*;

public class Led {

	public static void main(String[] args) {
		App theApp = new App("Blinking led!", 1000);

        Actuator led = new Actuator("led", 10);
        theApp.getActuators().add(led);

        Producer constant = new Producer("constant", true);
        theApp.getProducers().add(constant);

        Consumer blinker = new Consumer("blinker");
        Registry on = new Registry("on", true);
        on.setOwner(blinker);
        blinker.getMemory().add(on);
        blinker.getBehavior().add(new SetActuator(new Guard(on, true), HIGH, led));
        blinker.getBehavior().add(new SetActuator(new Guard(on, false), LOW, led));
        blinker.getBehavior().add(new SetRegistry(new Not(new ReadRegistry(on)), on));
        theApp.getConsumers().add(blinker);

        constant.getTargets().add(blinker); blinker.setTrigger(constant);

        Visitor codeGenerator = new ToC();
        theApp.accept(codeGenerator);

		// Writing C files
        try {
            System.out.println("Generating C code: ./output/main.c");
            String code =  codeGenerator.getCode().toString();
            System.out.println(code);
            Files.write(Paths.get("./output/main.c"), code.getBytes());
            System.out.println("Code generation: done");
            System.out.println("Board upload : cd output && make upload && cd ..;");
        } catch (IOException e) {
            System.err.println(e);
        }
	}

}
