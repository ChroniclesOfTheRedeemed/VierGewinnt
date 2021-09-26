/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VierGewinntPlayers;

import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import Games.VierGewinnt.VierGewinntPlayer;
import Interfaces.Game;
import Interfaces.InputListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.GameParticipants;

/**
 *
 * @author absea
 */
public class PseudoSpiel4G extends Dev4GSpiel {
    
    
    public PseudoSpiel4G(){
        super(new GameParticipants(
                new PseudoPlayer(), 
                new PseudoPlayer(), 
                new ArrayList())
        );
    }
    
    public void setGameState(GameState state){
        this.SpielFeld = deepCopy(state.SpielFeld);
        this.player1turn = state.player1turn;
        this.currentGameStatus = state.lastGameResult;
        //this.gameStillProgressing = state.gameInProgress;
    }
    
    //doesnt use copy (yet)
    public GameState getGameState(){
        GameState result = new GameState();
        result.SpielFeld = SpielFeld;
        result.player1turn = this.player1turn;
        result.lastGameResult = this.currentGameStatus;
       // result.gameInProgress = this.gameStillProgressing;
        return result;
    }
    public void move(int move) throws InvalidMoveException, GameStateException{
        if (player1turn){
            moveP1(move);
        } else {
            moveP2(move);
        }
    }
    public void moveP1(int move) throws InvalidMoveException, GameStateException{
        ((PseudoPlayer) player1).simulatemove(move);
    }
    
    public void moveP2(int move) throws InvalidMoveException, GameStateException{
        ((PseudoPlayer) player2).simulatemove(move);
    }
    
    public ArrayList<Integer> getEligetebleMoves(){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < this.getSpielFeldBreite(); i++) {
            try{
                this.checkMove(i);
                res.add(i);
            } catch (InvalidMoveException ex) {
                
            }
        }
        return res;
    }

    public static Boolean[][] deepCopy(Boolean[][] original) {
        if (original == null) {
            return null;
        }

        final Boolean[][] result = new Boolean[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);
            // For Java versions prior to Java 6 use the next:
            // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }
}

class PseudoPlayer implements VierGewinntPlayer{

    InputListener l;
    @Override
    public void gameStarted(Game gameRef, InputListener inputListener, boolean youHaveFirstMove) {
        l = inputListener;
    }
    
    public void simulatemove(int move) throws InvalidMoveException, GameStateException{
            l.inputGiven(move);
    }

    @Override
    public void makeMove(Integer enemyMove) {
        
    }

    @Override
    public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
        
    }
}
