/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import static Games.VierGewinnt.VierGewinntSpiel.SpielFeldBreite;
import static Games.VierGewinnt.VierGewinntSpiel.SpielFeldHöhe;
import Interfaces.Game;
import Interfaces.Game.GameResult;
import static Interfaces.Game.GameResult.GameWonForPlayer1;
import static Interfaces.Game.GameResult.GameWonForPlayer2;
import Interfaces.InputListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author absea
 */
public class Brutus implements Games.VierGewinnt.VierGewinntPlayer {

    int maxDepth = 5+1;
    boolean amPlayer1;
    GameState concurrentGameState = new GameState(new Boolean[SpielFeldBreite][SpielFeldHöhe], GameResult.GameStillProgressing);
    PseudoSpiel4G myGame = new PseudoSpiel4G();
    
    @Override
    public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
        myGame = new PseudoSpiel4G();
        concurrentGameState = new GameState(new Boolean[SpielFeldBreite][SpielFeldHöhe], GameResult.GameStillProgressing);
        }
    

    @Override
    public void makeMove(Integer enemyMove) {
        try {
            
            myGame.move(enemyMove);
        } catch (InvalidMoveException ex) {
            //Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GameStateException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
        }
        concurrentGameState = myGame.getGameState();
        recursiveValue d;
        try {
            d = investigateMove(concurrentGameState, maxDepth);
        } catch (GameStateException ex) {
            d = null;
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int returnMove;
        if(PersonalResult.IWIN.equals(d.result)){
            log("I choose you " + d.preferedMove, maxDepth);
            returnMove = d.preferedMove;
        } else {
            System.out.println("complete random is " + (int)(Math.random()*d.preferedMoves.size()));
            if(d.preferedMoves.isEmpty()){
                System.err.println("ooops I lost");
                returnMove = 0;
            } else {
                returnMove = d.preferedMoves.get((int)(Math.random()*1.0*d.preferedMoves.size()));
            }
        }
        
        try {
            System.out.println(d.preferedMoves.toString());
            myGame.move(returnMove);
            inputListener.inputGiven(returnMove);
        } catch (InvalidMoveException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GameStateException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected recursiveValue investigateMove(GameState state, int depth) throws GameStateException{
        depth--;
        ArrayList<Integer> randomMoves = new ArrayList<>();
        PersonalResult gamResult = PersonalResult.GAMEGOESON;
        int highestGameDepth = 0;
        
        int preferedMove = -1;
        for (int col = 0; col < state.SpielFeld.length; col++) {
            log("i move " + col, depth);
            PseudoSpiel4G game = new PseudoSpiel4G();
            game.playNewGame();
            game.setGameState(state);
            boolean work = true;
            try {
                game.move(col);
                //VierGewinntSpiel game = new VierGewinntSpiel(gameParticipants);
            } catch (InvalidMoveException ex) {
                //System.err.println("Top reached");
                work = false;
            } catch (GameStateException ex) {
                Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (work) {
                randomMoves.add(col);

                if (iWon(game.currentGameStatus)) {
                    preferedMove = col;
                    gamResult = PersonalResult.IWIN;
                    highestGameDepth = depth;
                    log("I win with " + col, depth);
                    break;
                }

                else if (game.currentGameStatus == GameResult.GameStillProgressing) {
                    if (depth > 0) {
                        recursiveValue recOvject = investigateEnemyMove(game.getGameState(), depth);
                        
                        switch (recOvject.result) {
                            case ILOSE:
                            case DRAW:
                                log("remove move " + col + " not winnable", depth);
                                randomMoves.remove((Integer) col);
                                break;
                            case IWIN:
                                if (recOvject.depth > highestGameDepth) { // ?
                                    highestGameDepth = recOvject.depth;// ?
                                    gamResult = PersonalResult.IWIN;
                                    preferedMove = col;
                                    log("Gotta Win With " + col, depth);
                                }
                            case GAMEGOESON:
                                log("Game goes on", depth);
                                break;
                            default:
                                System.err.println("uncaught case error");
                        }
                    }
                }
            }
        }
        recursiveValue val;
        if (!randomMoves.isEmpty()) {
            val = new recursiveValue(gamResult, highestGameDepth, randomMoves, preferedMove);
        } else/* if(gamResult != PersonalResult.IWIN)*/{
            val = new recursiveValue(PersonalResult.ILOSE, highestGameDepth, randomMoves, preferedMove);
        }
        return val;
    }
    
    protected recursiveValue investigateEnemyMove(GameState state, int depth) throws GameStateException{
        depth--;
        PersonalResult gameResut = PersonalResult.GAMEGOESON;
        int highestWinDepth = 0;
        ArrayList<GameState> gameStatelist = new ArrayList<>();
        
        for (int col = 0; col < state.SpielFeld.length ;col++) {
            PseudoSpiel4G game = new PseudoSpiel4G();
            game.playNewGame();
            game.setGameState(state);
            boolean work = true;
            
            try {
                game.move(col);
               // System.err.println("Top reached");
            } catch (InvalidMoveException ex) {
                work = false;
            } catch (GameStateException ex) {
                Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (work) {
                if (game.currentGameStatus != GameResult.GameStillProgressing) {
                    if (!iWon(game.currentGameStatus)) { //-niet
                        gameResut = PersonalResult.ILOSE;
                        log("I bims 1 bigz nubz " + col, depth);
                        break;
                    } else {
                        System.out.println("Meep Meep Meep");
                    }
                } else {
                    gameStatelist.add(game.getGameState());
                }
            }
        }
        boolean completeVictory = false;
        if(depth > 0){
            if(gameResut==PersonalResult.GAMEGOESON){
                completeVictory = true;
                for (int col = 0; col < gameStatelist.size(); col++) {
                    log("enemy Move" + col, depth);
                    recursiveValue val = investigateMove(gameStatelist.get(col), depth);
                    switch (val.result) {
                        case ILOSE:
                        case DRAW:
                            gameResut = val.result;
                            completeVictory = false; //originally not included 
                            log("enemy wins with " + col, depth);//also shouldnt i write complete Victory false herre??
                            break;
                        case IWIN:
                            if (highestWinDepth < val.depth) {
                                highestWinDepth = val.depth;//?
                            }
                            break;
                        case GAMEGOESON:
                            completeVictory = false;
                            break;
                        default:
                            System.err.println("errr waht am i doing");
                    }

                }
            }
        }
        if (completeVictory){
            log("complete Victory", depth);
            gameResut = PersonalResult.IWIN;
        }
        recursiveValue valu = new recursiveValue(
                gameResut, highestWinDepth, null, 0);
        return valu;
    }
    

    private boolean iWon(GameResult res) {
        return amPlayer1 ? res.equals(GameWonForPlayer1) : res.equals(GameWonForPlayer2);
    }

    protected void log(String message, int depth) {
//        String tabs = "";
//        for (int tab = 0; tab < maxDepth-depth; tab++) {
//            tabs += "|\t";
//        }
//        System.out.println(tabs + message);
    }

    InputListener<Integer> inputListener;
    
    @Override
    public void gameStarted(Game gameRef, InputListener<Integer> inputListener, boolean youHaveFirstMove) {
        amPlayer1 = youHaveFirstMove;
        this.inputListener = inputListener;
        try {
            myGame.playNewGame();
        } catch (GameStateException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    class recursiveValue{
        public PersonalResult result;
        public int depth;
        public ArrayList<Integer> preferedMoves = new ArrayList<>();
        public int preferedMove;

        public recursiveValue(PersonalResult result, int depth, ArrayList<Integer> preferedMoves, int preferedMove) {
            this.result = result;
            this.depth = depth;
            this.preferedMoves = preferedMoves;
            this.preferedMove = preferedMove;
        }
        public recursiveValue(){
            
        }
    }
}
