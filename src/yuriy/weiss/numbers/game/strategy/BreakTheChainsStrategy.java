package yuriy.weiss.numbers.game.strategy;

import yuriy.weiss.numbers.game.model.NumberInRow;
import yuriy.weiss.numbers.game.model.RowOfNumbers;

import java.util.ArrayList;
import java.util.List;

public class BreakTheChainsStrategy extends MaxNeighbourStrategy {

    @Override
    public NumberInRow selectNumberToRemove( final RowOfNumbers rowOfNumbers ) {
        final List<Integer> threeElementsChain = extractThreeElementsChain( rowOfNumbers );
        if ( threeElementsChain == null ) {
            final NumberInRow result = selectOneNeighbourNumber( rowOfNumbers );
            if ( result == null ) {
                return super.selectNumberToRemove( rowOfNumbers );
            } else {
                return result;
            }
        } else {
            return rowOfNumbers.getByValue( threeElementsChain.get( 1 ) );
        }
    }

    private List<Integer> extractThreeElementsChain( final RowOfNumbers rowOfNumbers ) {
        final List<List<Integer>> chains = new ArrayList<>();
        RowOfNumbers clonedRow = new RowOfNumbers( rowOfNumbers );
        while ( clonedRow.size() > 0 ) {
            NumberInRow numberInRow = clonedRow.get( 0 );
            List<Integer> chain = buildChain( numberInRow, clonedRow );
            if ( chain.size() == 3 ) {
                chains.add( chain );
            }
            for ( Integer value : chain ) {
                clonedRow.removeNumber( clonedRow.getByValue( value ) );
            }
        }
        if ( !chains.isEmpty() ) {
            return chains.get( 0 );
        } else {
            return null;
        }
    }

    private List<Integer> buildChain( final NumberInRow numberInRow, final RowOfNumbers rowOfNumbers ) {
        final List<Integer> result = new ArrayList<>();
        final int startValue = numberInRow.getValue();
        result.add( startValue );
        int nextValue = startValue + 10;
        while ( rowOfNumbers.containsValue( nextValue ) ) {
            result.add( nextValue );
            nextValue += 10;
        }
        return result;
    }

    private NumberInRow selectOneNeighbourNumber( final RowOfNumbers rowOfNumbers ) {
        for ( NumberInRow number : rowOfNumbers.getNumbers() ) {
            if ( number.getNeighbours().size() == 1 ) {
                return number;
            }
        }
        return null;
    }
}
