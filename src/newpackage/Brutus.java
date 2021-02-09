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
import java.util.ArrayList;
import newpackage.GameState.STATUS;

/**
 *
 * @author absea
 */
public class Brutus implements Games.VierGewinnt.VierGewinntPlayer {

    int maxDepth = 4;
    

    @Override
    public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected void investigateMove(GameState state, int depth){
        depth--;
        ArrayList<Integer> randomMoves = new ArrayList<>();
        STATUS f = STATUS.GameGoesOn;
        int highestGameDepth = 0;
        for (int col = 0; col < state.SpielFeld.size(); col++) {
            //VierGewinntSpiel game = new VierGewinntSpiel(gameParticipants);
        }
    }
    
    protected void log(String message, int depth){
        String tabs = "";
        for (int tab = 0; tab < maxDepth-depth; tab++) {
            tabs += "|\t";
        }
        System.out.println(tabs + message);
    }

    @Override
    public void gameStarted(Game gameRef, InputListener<Integer> inputListener) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeMove(Integer enemyMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
