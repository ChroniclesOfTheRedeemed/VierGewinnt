/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import Exceptions.MoveNotAvailableException;
import Interfaces.Game;

/**
 *
 * @author absea
 */
public class Brutus implements Games.VierGewinnt.VierGewinntPlayer {

    @Override
    public void gameStarted(Game gameRef) {
       
    }

    @Override
    public void makeMove(Integer enemyMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getMove() throws MoveNotAvailableException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
