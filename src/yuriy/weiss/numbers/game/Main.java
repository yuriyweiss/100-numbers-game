package yuriy.weiss.numbers.game;

import yuriy.weiss.numbers.game.model.NumberInRow;
import yuriy.weiss.numbers.game.model.Player;
import yuriy.weiss.numbers.game.model.RowOfNumbers;
import yuriy.weiss.numbers.game.strategy.BreakTheChainsStrategy;
import yuriy.weiss.numbers.game.strategy.MinNeighbourStrategy;

public class Main {

    public static final int NUMBERS_COUNT = 100;

    public static void main( String[] args ) {
        final RowOfNumbers rowOfNumbers = new RowOfNumbers();
        fillRowOfNumbers( rowOfNumbers );
        rowOfNumbers.printState();
        final Player player1 = new Player( new BreakTheChainsStrategy() );
        final Player player2 = new Player( new MinNeighbourStrategy() );
        while ( rowOfNumbers.size() > 2 ) {
            playerMove( rowOfNumbers, player1, "player1" );
            playerMove( rowOfNumbers, player2, "player2" );
        }
        rowOfNumbers.printState();
    }

    private static void fillRowOfNumbers( final RowOfNumbers rowOfNumbers ) {
        for ( int i = 1; i <= NUMBERS_COUNT; i++ ) {
            rowOfNumbers.addNumber( new NumberInRow( i ) );
        }
    }

    private static void playerMove( final RowOfNumbers rowOfNumbers, final Player player, final String playerName ) {
        NumberInRow numberToRemove = player.nextMove( rowOfNumbers );
        System.out.println( playerName + " selected: " + numberToRemove );
        rowOfNumbers.removeNumber( numberToRemove );
        rowOfNumbers.printState();
    }
}
