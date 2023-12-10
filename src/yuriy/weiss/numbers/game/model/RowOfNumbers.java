package yuriy.weiss.numbers.game.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RowOfNumbers {

    private final List<NumberInRow> numbers = new ArrayList<>();

    public RowOfNumbers() {
    }

    public RowOfNumbers( final RowOfNumbers source ) {
        source.numbers.forEach( sourceNumber -> numbers.add( new NumberInRow( sourceNumber ) ) );
    }

    public RowOfNumbers( final int... values ) {
        for ( int value : values ) {
            addNumber( new NumberInRow( value ) );
        }
    }

    public int size() {
        return numbers.size();
    }

    public NumberInRow get( int index ) {
        return numbers.get( index );
    }

    public List<NumberInRow> getNumbers() {
        return Collections.unmodifiableList( numbers );
    }

    public void addNumber( final NumberInRow newNumber ) {
        if ( !numbers.contains( newNumber ) ) {
            numbers.add( newNumber );
            refreshAfterAdding( newNumber );
        }
    }

    private void refreshAfterAdding( final NumberInRow newNumber ) {
        for ( NumberInRow number : numbers ) {
            if ( number.getValue() == newNumber.getValue() ) {
                continue;
            }
            if ( Math.abs( number.getValue() - newNumber.getValue() ) == 10 ) {
                number.addNeighbour( newNumber );
                newNumber.addNeighbour( number );
            }
        }
    }

    public void removeNumber( final NumberInRow numberToRemove ) {
        if ( numbers.contains( numberToRemove ) ) {
            numbers.remove( numberToRemove );
            refreshAfterRemoving( numberToRemove );
        }
    }

    private void refreshAfterRemoving( final NumberInRow numberToRemove ) {
        for ( NumberInRow number : numbers ) {
            if ( number.getNeighbours().contains( numberToRemove.getValue() ) ) {
                number.removeNeighbour( numberToRemove );
            }
        }
    }

    public void printState() {
        final StringBuilder sb = new StringBuilder( "" );
        for ( int i = 0; i < numbers.size(); i++ ) {
            sb.append( numbers.get( i ).getValue() );
            if ( i < numbers.size() - 1 ) {
                sb.append( " " );
            }
        }
        System.out.println( sb );
    }

    public boolean containsValue( final int value ) {
        return numbers.contains( new NumberInRow( value ) );
    }

    public NumberInRow getByValue( final int value ) {
        final int index = numbers.indexOf( new NumberInRow( value ) );
        if ( index == -1 ) {
            throw new RuntimeException( "NumberInRow for value " + value + " not found." );
        }
        return numbers.get( index );
    }
}
