package io.github.mosser.arduinoml.reactive.model;

import io.github.mosser.arduinoml.reactive.generator.Visitable;
import io.github.mosser.arduinoml.reactive.generator.Visitor;

import java.util.Objects;

public class Registry implements Visitable {

    @Override public void accept(Visitor visitor) { visitor.visit(this); }

    private String name;
    private boolean value;
    private Consumer owner;

    public Registry(String name, boolean value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }
    public boolean getValue() { return value; }
    public void setValue(boolean value) { this.value = value; }

    public Consumer getOwner() { return owner; }

    public void setOwner(Consumer owner) { this.owner = owner; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registry registry = (Registry) o;
        return Objects.equals(name, registry.name);
    }

    @Override public int hashCode() {
        return Objects.hash(name);
    }
}
