package yuriy.weiss.numbers.game.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NumberInRow {

    private final int value;
    private final Set<Integer> neighbours = new HashSet<>();

    public NumberInRow( final int value ) {
        this.value = value;
    }

    public NumberInRow( final NumberInRow source ) {
        this.value = source.value;
        this.neighbours.addAll( source.neighbours );
    }

    public int getValue() {
        return value;
    }

    public void addNeighbour( final NumberInRow neighbour ) {
        neighbours.add( neighbour.getValue() );
    }

    public void addNeighbour( final Integer neighbourValue ) {
        neighbours.add( neighbourValue );
    }

    public boolean removeNeighbour( final NumberInRow neighbour ) {
        return neighbours.remove( neighbour.getValue() );
    }

    public boolean removeNeighbour( final Integer neighbourValue ) {
        return neighbours.remove( neighbourValue );
    }

    public Set<Integer> getNeighbours() {
        return Collections.unmodifiableSet( neighbours );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        NumberInRow that = ( NumberInRow ) o;

        return value == that.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public String toString() {
        return "NumberInRow{" +
                "value=" + value +
                ", neighbours=" + neighbours +
                '}';
    }
}
