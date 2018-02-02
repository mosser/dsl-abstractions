package io.github.mosser.arduinoml.reactive.model.actions.expressions;

import io.github.mosser.arduinoml.reactive.generator.Visitor;
import io.github.mosser.arduinoml.reactive.model.Registry;

public class ReadRegistry extends Expression {

    @Override public void accept(Visitor visitor) { visitor.visit(this); }

    private Registry target;

    public ReadRegistry(Registry target) { this.target = target; }

    public Registry getTarget() { return target; }
}
