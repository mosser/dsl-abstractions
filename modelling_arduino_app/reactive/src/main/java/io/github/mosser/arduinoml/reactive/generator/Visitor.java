package io.github.mosser.arduinoml.reactive.generator;

import io.github.mosser.arduinoml.reactive.model.actions.*;
import io.github.mosser.arduinoml.reactive.model.*;
import io.github.mosser.arduinoml.reactive.model.actions.expressions.*;


public abstract class Visitor {

	public abstract void visit(Actuator actuator);
	public abstract void visit(App app);
	public abstract void visit(Consumer consumer);
    public abstract void visit(Producer producer);
    public abstract void visit(Registry registry);
    public abstract void visit(SetActuator setActuator);
    public abstract void visit(SetRegistry setRegistry);
    public abstract void visit(Guard guard);
    //     public abstract void visit(Action action);
    public abstract void visit(Not not);
    public abstract void visit(ReadRegistry readRegistry);
    //     public abstract void visit(Expression e);


	/***********************
	 ** Helper mechanisms **
	 ***********************/

	StringBuffer code;

	public StringBuffer getCode() {
		return code;
	}

}

