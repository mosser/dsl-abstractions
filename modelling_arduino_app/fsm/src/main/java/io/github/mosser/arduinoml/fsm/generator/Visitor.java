package io.github.mosser.arduinoml.fsm.generator;

import io.github.mosser.arduinoml.fsm.model.State;
import io.github.mosser.arduinoml.fsm.model.Action;
import io.github.mosser.arduinoml.fsm.model.Actuator;
import io.github.mosser.arduinoml.fsm.model.App;

public abstract class Visitor {

	public abstract void visit(App app);
	public abstract void visit(State state);
	public abstract void visit(Action action);
	public abstract void visit(Actuator actuator);

	/***********************
	 ** Helper mechanisms **
	 ***********************/

	StringBuffer code;

	public StringBuffer getCode() {
		return code;
	}

}

