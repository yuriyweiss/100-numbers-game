package yuriy.weiss.numbers.game.strategy;

import yuriy.weiss.numbers.game.model.NumberInRow;
import yuriy.weiss.numbers.game.model.RowOfNumbers;

public class MaxNeighbourStrategy implements SelectNumberStrategy {

    @Override
    public NumberInRow selectNumberToRemove( final RowOfNumbers rowOfNumbers ) {
        int maxNeighbours = 0;
        NumberInRow result = null;
        for ( int i = 0; i < rowOfNumbers.size(); i++ ) {
            NumberInRow current = rowOfNumbers.get( i );
            if ( current.getNeighbours().size() > maxNeighbours ) {
                maxNeighbours = current.getNeighbours().size();
                result = current;
            }
        }
        return result;
    }
}
