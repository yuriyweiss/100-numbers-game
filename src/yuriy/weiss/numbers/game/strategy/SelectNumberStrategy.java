package yuriy.weiss.numbers.game.strategy;

import yuriy.weiss.numbers.game.model.NumberInRow;
import yuriy.weiss.numbers.game.model.RowOfNumbers;

public interface SelectNumberStrategy {

    NumberInRow selectNumberToRemove( final RowOfNumbers rowOfNumbers );
}
