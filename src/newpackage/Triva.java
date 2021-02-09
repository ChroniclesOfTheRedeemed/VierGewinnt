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
import Interfaces.InputListener;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class Triva implements ChompPlayer {

    Game gameReGame;
    InputListener<Dimension> inputListener;
    Dimension myMove;


    @Override
    public void gameStarted(Game gameRef, InputListener<Dimension> inputListener) {
        gameReGame = gameRef;
        this.inputListener = inputListener;
    }
    
    @Override
    public void makeMove(Dimension enemyMove) {
        try {
            if (enemyMove == Games.Chomp.ChompGame.FirstMove) {
                enemyMove = new Dimension(gameReGame.getSpielFeldBreite() - 1, gameReGame.getSpielFeldHöhe() - 1);
            }
            int height;
            int width;
            switch ((int) (Math.random() * 3)) {
                case 0:
                    height = enemyMove.height - 1;
                    width = enemyMove.width - 1;
                    break;
                case 1:
                    height = enemyMove.height;
                    width = enemyMove.width - 1;
                    break;
                default:
                    height = enemyMove.height - 1;
                    width = enemyMove.width;
                    break;
            }
            myMove = new Dimension(width, height);
            inputListener.inputGiven(enemyMove);
        } catch (GameStateException ex) {
            Logger.getLogger(randyplayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMoveException ex) {
            makeMove(enemyMove);
        }
    }

    @Override
    public void gameEnded(Dimension finishingMove, Game.GameResult gameResult) {

    }

    @Override
    public String toString() {
        return "Triva";
    }
}
