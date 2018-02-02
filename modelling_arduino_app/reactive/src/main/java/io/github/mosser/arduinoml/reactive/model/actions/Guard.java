package io.github.mosser.arduinoml.reactive.model.actions;

import io.github.mosser.arduinoml.reactive.generator.Visitable;
import io.github.mosser.arduinoml.reactive.generator.Visitor;
import io.github.mosser.arduinoml.reactive.model.Registry;

public class Guard implements Visitable {

    @Override public void accept(Visitor visitor) { visitor.visit(this); }

    private Registry target;
    private boolean expectation;

    public Guard(Registry target, boolean expectation) {
        this.target = target;
        this.expectation = expectation;
    }

    public Registry getTarget() { return target; }
    public boolean getExpectation() { return expectation; }

}
