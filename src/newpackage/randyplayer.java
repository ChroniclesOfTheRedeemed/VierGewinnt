/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackage;

import java.util.logging.Level;
import java.util.logging.Logger;
import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import Exceptions.MoveNotAvailableException;
import Interfaces.Game;
import Interfaces.Player;
import Games.VierGewinnt.VierGewinntSpiel;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class randyplayer implements Games.VierGewinnt.VierGewinntPlayer {

    Game gameref;
    @Override
    public void gameStarted(Game gameRef) {
        gameref = gameRef;
    }

    @Override
    public void makeMove(Integer enemyMove) {
        try {
            gameref.playerMadeMove();
        } catch (GameStateException | MoveNotAvailableException ex) {
            Logger.getLogger(randyplayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMoveException ex) {
            makeMove(enemyMove);
        }
    }

    @Override
    public Integer getMove() throws MoveNotAvailableException {
        return (int) (Math.random() * (VierGewinntSpiel.SpielFeldBreite));//must be less than Spielfeldbreite and will be floored anyway = nice)
    }

    @Override
    public void gameEnded(Integer move, VierGewinntSpiel.GameResult f) {
        
    }

    @Override
    public String toString() {
        return "Randy"; //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
