package io.github.mosser.arduinoml.reactive.model;

import io.github.mosser.arduinoml.reactive.generator.Visitable;
import io.github.mosser.arduinoml.reactive.generator.Visitor;

import java.util.HashSet;
import java.util.Set;

public class App implements Visitable{

    @Override public void accept(Visitor visitor) { visitor.visit(this); }

    private Set<Producer> producers;
    private Set<Consumer> consumers;
    private Set<Actuator> actuators;
    private String name;
    private int period;

    public App(String name, int period) {
        this.name = name;
        this.period = period;
        this.producers = new HashSet<>();
        this.actuators = new HashSet<>();
        this.consumers = new HashSet<>();
    }

    public Set<Producer> getProducers() { return producers; }
    public Set<Consumer> getConsumers() { return consumers; }
    public Set<Actuator> getActuators() { return actuators; }
    public String getName() { return name; }
    public int getPeriod()  { return period; }

}
