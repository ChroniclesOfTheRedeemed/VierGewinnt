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

    int maxDepth = 4;
    boolean amPlayer1;
    GameState concurrentGameState = new GameState(new Boolean[SpielFeldBreite][SpielFeldHöhe], null);
    PseudoSpiel4G myGame = new PseudoSpiel4G();
    
    @Override
    public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
        
        }
    

    @Override
    public void makeMove(Integer enemyMove) {
        try {
            myGame.move(enemyMove);
        } catch (InvalidMoveException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GameStateException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
        }
        concurrentGameState = myGame.getGameState();
        int depth = maxDepth;
        ArrayList<Integer> randomMoves = new ArrayList<>();
        int highestGameDepth = 0;
        boolean gameInProgress = true;
        PersonalResult gamResult = null;
        
        int winningCol = 0;
        for (int col = 0; col < concurrentGameState.SpielFeld.length; col++) {
            log("I move1 " + col, depth);
            PseudoSpiel4G game = new PseudoSpiel4G();
            try {
                game.playNewGame();
            } catch (GameStateException ex) {
                Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
            }
            game.setGameState(concurrentGameState);
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
                if (!game.gameStillProgressing) {
                    if(iWon(game.lastGameResult)){
                        gamResult = PersonalResult.IWIN;
                        highestGameDepth = depth;
                        log("I win with " + col, depth);
                        winningCol = col;
                        break;
                    }
                } else {
                    if (depth > 0) {
                        recursiveValue recOvject = null;
                        try {
                            recOvject = investigateEnemyMove(game.getGameState(), depth);
                        } catch (GameStateException ex) {
                            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (recOvject.gameInProgress) {
                            log("Game goes on", depth);
                        } else {
                            switch (recOvject.result) {
                                case ILOSE:
                                case DRAW:
                                    log("remove move " + col + " not winnable", depth);
                                    randomMoves.remove(randomMoves.indexOf(col));
                                    break;
                                case IWIN:
                                    if (recOvject.depth > highestGameDepth) { // should?? be like that?
                                        highestGameDepth = recOvject.depth;//defuuuq
                                        gamResult = PersonalResult.IWIN;
                                        log("Gotta Win With " + col, depth);
                                        winningCol = col;
                                    }
                            }

                        }
                    }
                }
            }
        }
        int returnMove = -1;
        recursiveValue val = new recursiveValue();
        val.gameInProgress = gameInProgress;
        if (!randomMoves.isEmpty()) {
            val.depth = depth;
            val.result = gamResult;
            val.gameInProgress = gameInProgress;
        } else if(!PersonalResult.IWIN.equals(gamResult)){
            val.result = PersonalResult.ILOSE;
        }
        if(PersonalResult.IWIN.equals(gamResult)){
            log("I choose you " + winningCol, maxDepth);
            returnMove = winningCol;
        } else {
            System.out.println("complete random is " + (int)(Math.random()*randomMoves.size()));
            returnMove = randomMoves.get((int)(Math.random()*1.0*randomMoves.size()));
        }
        
        try {
            System.out.println(randomMoves.toString());
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
        int highestGameDepth = 0;
        boolean gameInProgress = true;
        PersonalResult gamResult = null;
        
        for (int col = 0; col < state.SpielFeld.length; col++) {
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
                gameInProgress = game.gameStillProgressing;
                if (!game.gameStillProgressing) {
                    if(iWon(game.lastGameResult)){
                        gamResult = PersonalResult.IWIN;
                        highestGameDepth = depth;
                        log("I win with " + col, depth);
                        break;
                    }
                } else {
                    if (depth > 0) {
                        recursiveValue recOvject = investigateEnemyMove(game.getGameState(), depth);
                        if (recOvject.gameInProgress) {
                            log("Game goes on", depth);
                        } else {
                            switch (recOvject.result) {
                                case ILOSE:
                                case DRAW:
                                    log("remove move " + col + " not winnable", depth);
                                    randomMoves.remove(col);
                                    break;
                                case IWIN:
                                    if (recOvject.depth > highestGameDepth) { // should?? be like that?
                                        highestGameDepth = recOvject.depth;//defuuuq
                                        gamResult = PersonalResult.IWIN;
                                        log("Gotta Win With " + col, depth);
                                    }
                            }

                        }
                    }
                }
            }
        }
        recursiveValue val = new recursiveValue();
        val.gameInProgress = gameInProgress;
        if (!randomMoves.isEmpty()) {
            val.depth = depth;
            val.result = gamResult;
        } else if(!PersonalResult.IWIN.equals(gamResult)){
            val.result = PersonalResult.ILOSE;
        }
        return val;
    }
    
    protected recursiveValue investigateEnemyMove(GameState state, int depth) throws GameStateException{
        depth--;
        PersonalResult gameResut = null;
        boolean gameProgressing = true;
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
            if(work) {
                gameProgressing = game.gameStillProgressing;
                if (game.gameStillProgressing) {
                    gameStatelist.add(state);
                } else {
                    if (!iWon(game.lastGameResult)) {
                        gameResut = PersonalResult.ILOSE;
                        log("I bims 1 bigz nubz " + col, depth);
                        break;
                    } else {
                        System.out.println("Meep Meep Meep");
                    }
                }
            }
        }
        boolean completeVictory = false;
        if(depth > 0){
            if(gameProgressing){
                completeVictory = true;
                for (int col = 0; col < gameStatelist.size(); col++) {
                    log("enemy Move" + col, depth);
                    recursiveValue val = investigateMove(gameStatelist.get(col), depth);
                    PersonalResult gRes = val.result;
                    if(!val.gameInProgress){
                        switch(gRes){
                            case ILOSE:
                            case DRAW:
                                gameProgressing = false;
                                gameResut = gRes;
                                //completeVictory = false;
                                log("enemy wins with " + col, depth);//also shouldnt i write complete Victory false herre??
                                break;
                            case IWIN:
                                if(highestWinDepth < val.depth){
                                    highestWinDepth = val.depth;//MUST BE WRONG NO????
                                }
                                break;
                            default:
                                System.err.println("errr waht am i doing");
                        }
                    } else {
                        gameProgressing = true;
                        completeVictory = false;
                    }
                }
            }
        }
        if (completeVictory){
            log("complete Victory", depth);
            gameResut = PersonalResult.IWIN;
        }
        recursiveValue valu = new recursiveValue(
                gameResut, highestWinDepth, gameProgressing);
        return valu;
    }
    

    private boolean iWon(GameResult res) {
        return amPlayer1 ? res.equals(GameWonForPlayer1) : res.equals(GameWonForPlayer2);
    }

    protected void log(String message, int depth) {
        String tabs = "";
        for (int tab = 0; tab < maxDepth-depth; tab++) {
            tabs += "|\t";
        }
        System.out.println(tabs + message);
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
        public boolean gameInProgress;
        public int depth;

        public recursiveValue(PersonalResult result, int depth, boolean gameInProgress) {
            this.result = result;
            this.depth = depth;
            this.gameInProgress = gameInProgress;
        }
        public recursiveValue(){
            
        }
    }
}
