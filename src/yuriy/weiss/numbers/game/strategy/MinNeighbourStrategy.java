package yuriy.weiss.numbers.game.strategy;

import yuriy.weiss.numbers.game.model.NumberInRow;
import yuriy.weiss.numbers.game.model.RowOfNumbers;

public class MinNeighbourStrategy implements SelectNumberStrategy {

    @Override
    public NumberInRow selectNumberToRemove( final RowOfNumbers rowOfNumbers ) {
        if ( rowOfNumbers.size() > 5 ) {
            return selectByMinNeighbours( rowOfNumbers );
        } else {
            return selectToWin( rowOfNumbers );
        }
    }

    private NumberInRow selectByMinNeighbours( final RowOfNumbers rowOfNumbers ) {
        int minNeighbours = 10;
        NumberInRow result = null;
        for ( int i = 0; i < rowOfNumbers.size(); i++ ) {
            NumberInRow current = rowOfNumbers.get( i );
            if ( current.getNeighbours().size() < minNeighbours ) {
                minNeighbours = current.getNeighbours().size();
                result = current;
            }
        }
        return result;
    }

    private NumberInRow selectToWin( final RowOfNumbers rowOfNumbers ) {
        NumberInRow result = null;
        final NumberInRow zeroNeighbours = getZeroNeighboursNumber( rowOfNumbers );
        if ( zeroNeighbours != null ) {
            return zeroNeighbours;
        } else {
            for ( int i = 0; i < rowOfNumbers.size(); i++ ) {
                final NumberInRow candidate = rowOfNumbers.get( i );
                final RowOfNumbers cloneRow = new RowOfNumbers( rowOfNumbers );
                cloneRow.removeNumber( candidate );
                if ( getZeroNeighboursNumber( cloneRow ) == null ) {
                    result = candidate;
                    break;
                }
            }
            return result;
        }
    }

    private NumberInRow getZeroNeighboursNumber( final RowOfNumbers rowOfNumbers ) {
        return rowOfNumbers.getNumbers().stream()
                .filter( numberInRow -> numberInRow.getNeighbours().isEmpty() )
                .findFirst()
                .orElse( null );
    }
}
