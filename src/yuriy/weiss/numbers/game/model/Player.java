package yuriy.weiss.numbers.game.model;

import yuriy.weiss.numbers.game.strategy.SelectNumberStrategy;

public class Player {

    private final SelectNumberStrategy strategy;

    public Player( final SelectNumberStrategy strategy ) {
        this.strategy = strategy;
    }

    public NumberInRow nextMove( final RowOfNumbers rowOfNumbers ) {
        return strategy.selectNumberToRemove( rowOfNumbers );
    }
}
