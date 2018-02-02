package io.github.mosser.arduinoml.reactive.model.actions;

import io.github.mosser.arduinoml.reactive.generator.Visitor;
import io.github.mosser.arduinoml.reactive.model.Registry;
import io.github.mosser.arduinoml.reactive.model.actions.expressions.Expression;

import java.util.Optional;

public class SetRegistry extends Action {

    @Override public void accept(Visitor visitor) { visitor.visit(this); }

    private Expression expression;
    private Registry target;

    public SetRegistry(Expression expression, Registry target) {
        this.expression = expression;
        this.target = target;
    }

    public SetRegistry(Guard guard, Expression expression, Registry target) {
        this(expression, target);
        this.guard = Optional.of(guard);
    }

    public Expression getExpression() { return expression; }
    public Registry getTarget() { return target; }

}

