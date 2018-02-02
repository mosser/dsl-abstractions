package io.github.mosser.arduinoml.reactive.model;

import io.github.mosser.arduinoml.reactive.generator.Visitable;
import io.github.mosser.arduinoml.reactive.generator.Visitor;

import java.util.Objects;

public class Actuator implements Visitable {
    @Override public void accept(Visitor visitor) { visitor.visit(this); }

    private String name;
    private int pin;

    public Actuator(String name, int pin) {
        this.name = name;
        this.pin = pin;
    }

    public String getName() { return name; }
    public int getPin() { return pin; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actuator actuator = (Actuator) o;
        return pin == actuator.pin &&
                Objects.equals(name, actuator.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pin);
    }
}
