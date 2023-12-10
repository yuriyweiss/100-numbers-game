package yuriy.weiss.numbers.game.strategy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import yuriy.weiss.numbers.game.model.NumberInRow;
import yuriy.weiss.numbers.game.model.RowOfNumbers;

public class MinNeighbourStrategyTest {

    @Test
    public void testOrdinaryDistribution() {
        final SelectNumberStrategy strategy = new MinNeighbourStrategy();
        final RowOfNumbers rowOfNumbers = new RowOfNumbers( 8, 17, 18, 27, 45 );
        NumberInRow selected = strategy.selectNumberToRemove( rowOfNumbers );
        Assertions.assertThat( selected.getValue() ).isEqualTo( 45 );

        rowOfNumbers.removeNumber( new NumberInRow( 27 ) );
        selected = strategy.selectNumberToRemove( rowOfNumbers );
        Assertions.assertThat( selected.getValue() ).isEqualTo( 17 );
    }

    @Test
    public void testDefeatDistribution() {
        final SelectNumberStrategy strategy = new MinNeighbourStrategy();
        final RowOfNumbers rowOfNumbers = new RowOfNumbers( 8, 17, 18, 27, 37 );
        NumberInRow selected = strategy.selectNumberToRemove( rowOfNumbers );
        System.out.println( selected );
        Assertions.assertThat( selected.getValue() ).isEqualTo( 17 );
        rowOfNumbers.removeNumber( new NumberInRow( 17 ) );
        rowOfNumbers.printState();

        rowOfNumbers.removeNumber( new NumberInRow( 27 ) );
        rowOfNumbers.printState();
        selected = strategy.selectNumberToRemove( rowOfNumbers );
        System.out.println( selected );
        Assertions.assertThat( selected.getValue() ).isEqualTo( 37 );
        rowOfNumbers.removeNumber( selected );
        rowOfNumbers.printState();
    }
}
