/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import Exceptions.MoveNotAvailableException;
import Games.Chomp.ChompPlayer;
import Games.VierGewinnt.VierGewinntSpiel;
import Interfaces.Game;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class ChompRandy implements ChompPlayer {

    Game gameReGame;
    Dimension myMove = new Dimension();
    Dimension outerRing;

    @Override
    public void gameStarted(Game gameRef) {
        gameReGame = gameRef;
    }

    @Override
    public void makeMove(Dimension enemyMove) {
        try {
            outerRing = new Dimension(gameReGame.getSpielFeldBreite(), gameReGame.getSpielFeldBreite());
            myMove.width = (int) (Math.random() * outerRing.width);
            myMove.height = (int) (Math.random() * outerRing.height);
            gameReGame.playerMadeMove();
        } catch (GameStateException | MoveNotAvailableException ex) {
            System.err.println("Uncaught Manual ChompRandy.MakeMove Exception ");
        } catch (InvalidMoveException ex) {
            reCalcMove();
        }
    }

    private void reCalcMove() {
        try {
            if (outerRing.height > outerRing.width) {
                outerRing.height--;
            } else {
                outerRing.width--;
            }
            myMove.width = (int) (Math.random() * outerRing.width);
            myMove.height = (int) (Math.random() * outerRing.height);
            gameReGame.playerMadeMove();
        } catch (GameStateException | MoveNotAvailableException ex) {
            Logger.getLogger(ChompRandy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMoveException ex) {
            reCalcMove();
        }
    }

    @Override
    public Dimension getMove() throws MoveNotAvailableException {
        return myMove;
    }

    @Override
    public void gameEnded(Dimension finishingMove, Game.GameResult gameResult) {

    }

    @Override
    public String toString() {
        return "Randy";
    }
}
