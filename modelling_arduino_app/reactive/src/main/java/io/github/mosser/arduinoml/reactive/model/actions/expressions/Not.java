package io.github.mosser.arduinoml.reactive.model.actions.expressions;

import io.github.mosser.arduinoml.reactive.generator.Visitor;

public class Not extends Expression {

    @Override public void accept(Visitor visitor) { visitor.visit(this); }

    private Expression e;

    public Not(Expression e) { this.e = e; }

    public Expression getE() { return e; }
}
