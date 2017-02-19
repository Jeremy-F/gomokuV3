package fr.esiee;


import fr.esiee.Player.Player;
import fr.esiee.Player.RandomPlayer;
import fr.esiee.Player.MaxScorePlayer;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jeremyfornarino on 17/02/2017.
 */
public class TestBoard {
    Player[] players = new Player[2];
    Board board;
    @Before
    public void setUp() throws Exception {
        Player alexandre = new MaxScorePlayer("Alexandre", Color.BLUE);
        Player jeremy = new MaxScorePlayer("Jérémy", Color.RED);
        this.players[0] = alexandre;
        this.players[1] = jeremy;
        board = new Board(3, 3, alexandre, jeremy);
    }
    @Test
    public void testGetLastMovement(){
        board.play(0,0);
        board.play(0,1);
        board.play(0,2);
        Movement lastMovement = board.getLastMove();
        assertTrue("Le Last Movement n'est pas bon", lastMovement.getLine() == 0 && lastMovement.getColumn() == 2);
    }
    @Test
    public void testGetLastMoveAfterClean(){
        testGetLastMovement();
        System.out.println(board);
        board.cancelLastMove();
        board.cancelLastMove();
        Movement lastMovement = board.getLastMove();
        System.out.println(lastMovement);
        assertTrue("Le Last Movement n'est pas bon apres supression", lastMovement.getLine() == 0 && lastMovement.getColumn() == 0);
    }
    @Test
    public void verifyEarnedByPlayerFirstBox() throws Exception {
        Alignment alignment = new Alignment(3);
        Player player = new RandomPlayer("Person", null);
        alignment.addAll(new Box(0,0), new Box(0,1), new Box(0,2).setOwner(player), new Box(0,3).setOwner(player), new Box(0,4).setOwner(player));
        boolean condition = alignment.earnedBy(player);
        assertTrue("Error occured - earnedByPlayer : FALSE", condition);
    }    @Test
    public void verifyEarnedByPlayerLasttBox() throws Exception {

        Alignment alignment = new Alignment(3);
        Player player = new RandomPlayer("Person", null);
        alignment.addAll(new Box(0,2).setOwner(player), new Box(0,3).setOwner(player), new Box(0,4).setOwner(player));
        boolean condition = alignment.earnedBy(player);
        assertTrue("Arg, j'ai pas gagné", condition);
    }
    @Test
    public void testAllEmptyBox(){
        testGetLastMoveAfterClean();
        System.out.println(board.toString());

    }

    @Test
    public void testOtherPlayer(){
        assertTrue("The other player is not the good one", board.getOtherPlayer(this.players[0]).equals(this.players[1]));
    }

    @Test
    public void testScoreFor(){
        assertTrue("The score of an empty board is not good ", board.scoreFor(players[0]) == board.scoreFor(players[1]));
        board.play(0,0);
        board.play(1,0);
        assertTrue("The score of a board wis only 1 move by player is not good ", board.scoreFor(players[0]) == board.scoreFor(players[1]));
        board.play(0,1);
        board.play(1,2);
        assertTrue("The score of an empty board is not good ", board.scoreFor(players[0]) == 1);
        //board.play(0,2);
    }

    @Test
    public void testAlignementCanBeWin(){
        Alignment alignment = new Alignment(4);
        alignment.add(new Box(0,0));
        alignment.add(new Box(0,0));
        alignment.add(new Box(0,0));
        alignment.add(new Box(0,0));
        assertTrue("An empty alignment have to be wonable", alignment.canBeWin(8));
    }

}
