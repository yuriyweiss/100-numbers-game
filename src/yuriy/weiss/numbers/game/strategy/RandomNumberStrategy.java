package yuriy.weiss.numbers.game.strategy;

import yuriy.weiss.numbers.game.model.NumberInRow;
import yuriy.weiss.numbers.game.model.RowOfNumbers;

import java.util.Random;

public class RandomNumberStrategy implements SelectNumberStrategy {

    private final Random random = new Random();

    public RandomNumberStrategy() {
        random.setSeed( System.currentTimeMillis() );
    }

    @Override
    public NumberInRow selectNumberToRemove( RowOfNumbers rowOfNumbers ) {
        int index = random.nextInt( rowOfNumbers.size() );
        return rowOfNumbers.get( index );
    }
}
