package io.github.mosser.arduinoml.reactive.model.actions;

import io.github.mosser.arduinoml.reactive.generator.Visitor;
import io.github.mosser.arduinoml.reactive.model.Actuator;
import io.github.mosser.arduinoml.reactive.model.Registry;
import io.github.mosser.arduinoml.reactive.model.SIGNAL;

import java.util.Optional;

public class SetActuator extends Action {

    @Override public void accept(Visitor visitor) { visitor.visit(this); }

    private SIGNAL value;
    private Actuator target;

    public SetActuator(SIGNAL value, Actuator target) {
        this.value = value;
        this.target = target;
    }

    public SetActuator(Guard guard, SIGNAL value, Actuator target) {
        this(value, target);
        this.guard = Optional.of(guard);
    }

    public SIGNAL getValue() { return value; }
    public Actuator getTarget() { return target; }
}
