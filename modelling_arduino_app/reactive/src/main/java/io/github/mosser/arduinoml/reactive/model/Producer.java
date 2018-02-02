package io.github.mosser.arduinoml.reactive.model;

import io.github.mosser.arduinoml.reactive.generator.Visitable;
import io.github.mosser.arduinoml.reactive.generator.Visitor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Producer implements Visitable {

    @Override public void accept(Visitor visitor) { visitor.visit(this); }

    private String name;
    private boolean value;
    private Set<Consumer> targets;

    public Producer(String name, boolean value) {
        this.name = name;
        this.value = value;
        this.targets = new HashSet<>();
    }

    public String getName() { return name; }
    public boolean getValue() { return value; }
    public Set<Consumer> getTargets() { return targets; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(name, producer.name);
    }

    @Override public int hashCode() { return Objects.hash(name); }
}