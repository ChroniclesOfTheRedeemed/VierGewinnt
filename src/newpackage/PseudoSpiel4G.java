/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import Exceptions.MoveNotAvailableException;
import Games.VierGewinnt.VierGewinntSpiel;
import Interfaces.Game;
import Interfaces.InputListener;
import Interfaces.Player;
import java.util.ArrayList;
import viergewinntpraxis.GameParticipants;

/**
 *
 * @author absea
 */
public class PseudoSpiel4G {
    VierGewinntSpiel fg;
    Player<Integer> player1 = new Player<Integer>() {
        @Override
        public void gameStarted(Game gameRef, InputListener<Integer> inputListener) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void makeMove(Integer enemyMove) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
        Player<Integer> player2 = new Player<Integer>() {
        @Override
        public void gameStarted(Game gameRef, InputListener<Integer> inputListener) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void makeMove(Integer enemyMove) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        };
    public PseudoSpiel4G(){

        GameParticipants f = new GameParticipants(player1, player2, new ArrayList());
        //fg = new VierGewinntSpiel(gameParticipants);
    }
            
}
