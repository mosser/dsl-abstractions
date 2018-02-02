package io.github.mosser.arduinoml.fsm.model;

import io.github.mosser.arduinoml.fsm.generator.Visitable;
import io.github.mosser.arduinoml.fsm.generator.Visitor;

public class Action implements Visitable {

	private SIGNAL value;
	private Actuator actuator;


	public SIGNAL getValue() {
		return value;
	}

	public void setValue(SIGNAL value) {
		this.value = value;
	}

	public Actuator getActuator() {
		return actuator;
	}

	public void setActuator(Actuator actuator) {
		this.actuator = actuator;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
