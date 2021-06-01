/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package VierGewinntPlayers;

import java.util.logging.Level;
import java.util.logging.Logger;
import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import Interfaces.Game;
import Games.VierGewinnt.VierGewinntSpiel;
import Interfaces.InputListener;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class randyplayer implements Games.VierGewinnt.VierGewinntPlayer {

    Game gameref;
    InputListener<Integer> inputListener;
    //private Game gameRef;

    @Override
    public void gameStarted(Game gameRef, InputListener<Integer> inputListener, boolean youHaveFirstMove) {
        this.gameref = gameRef;
        this.inputListener = inputListener;
    }

    @Override
    public void makeMove(Integer enemyMove) {
        try {
            inputListener.inputGiven(
                    (int) (Math.random() * (VierGewinntSpiel.SpielFeldBreite)));//must be less than Spielfeldbreite and will be floored anyway = nice)
        } catch (InvalidMoveException ex) {
            makeMove(enemyMove);
        } catch (GameStateException ex) {
            Logger.getLogger(randyplayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void gameEnded(Integer move, VierGewinntSpiel.GameResult f) {
        
    }

    @Override
    public String toString() {
        return "Randy"; //To change body of generated methods, choose Tools | Templates.
    }
}
