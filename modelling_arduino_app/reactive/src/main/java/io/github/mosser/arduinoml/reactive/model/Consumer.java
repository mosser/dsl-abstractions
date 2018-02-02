package io.github.mosser.arduinoml.reactive.model;

import io.github.mosser.arduinoml.reactive.generator.Visitable;
import io.github.mosser.arduinoml.reactive.generator.Visitor;
import io.github.mosser.arduinoml.reactive.model.actions.Action;

import java.util.*;

public class Consumer implements Visitable {

    @Override public void accept(Visitor visitor) { visitor.visit(this); }

    private String name;
    private List<Action> behavior;
    private Set<Registry> memory = new HashSet<>();
    private Producer trigger;

    public Consumer(String name) {
        this.name = name;
        this.behavior = new LinkedList<>();
    }

    public String getName() { return name; }
    public List<Action> getBehavior() { return behavior; }
    public Set<Registry> getMemory() { return memory; }
    public Producer getTrigger() { return trigger; }
    public void setTrigger(Producer trigger) { this.trigger = trigger;  }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return Objects.equals(name, consumer.name);
    }

    @Override public int hashCode() { return Objects.hash(name); }
}
