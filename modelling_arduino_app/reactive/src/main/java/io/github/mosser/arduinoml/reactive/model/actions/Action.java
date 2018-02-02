package io.github.mosser.arduinoml.reactive.model.actions;

import io.github.mosser.arduinoml.reactive.generator.Visitable;

import java.util.Optional;

public abstract class Action implements Visitable {

    protected Optional<Guard> guard = Optional.empty();

    public Optional<Guard> getGuard() { return guard; }
}
