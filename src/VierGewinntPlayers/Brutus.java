/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VierGewinntPlayers;

import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import Games.VierGewinnt.VierGewinntSpiel;
import static Games.VierGewinnt.VierGewinntSpiel.SpielFeldBreite;
import static Games.VierGewinnt.VierGewinntSpiel.SpielFeldHöhe;
import Interfaces.Game;
import Interfaces.Game.GameResult;
import static Interfaces.Game.GameResult.GameWonForPlayer1;
import static Interfaces.Game.GameResult.GameWonForPlayer2;
import Interfaces.InputListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author absea
 */
public class Brutus implements Games.VierGewinnt.VierGewinntPlayer {

    static final Logger logger = Logger.getLogger(Brutus.class.getName());

    int maxDepth = 5 + 1;
    boolean amPlayer1;
    GameState initialGameState = new GameState(new Boolean[SpielFeldBreite][SpielFeldHöhe], GameResult.GameStillProgressing);
    PseudoSpiel4G testGame = new PseudoSpiel4G();

    @Override
    public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
        testGame = new PseudoSpiel4G();
        initialGameState = new GameState(new Boolean[SpielFeldBreite][SpielFeldHöhe], GameResult.GameStillProgressing);
    }

    @Override
    public void makeMove(Integer enemyMove) {
        try {
            if (!Objects.equals(enemyMove, VierGewinntSpiel.FirstMove)) {
                testGame.move(enemyMove);
            }
        } catch (InvalidMoveException ex) {
            logger.log(Level.SEVERE, "Brutus Test Game crashed.", ex);
            //Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GameStateException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, "Brutus Test Game Crashed.", ex);
        }
        initialGameState = testGame.getGameState();
        InvestigationResult recursingValue;
        try {
            recursingValue = investigateMove(initialGameState, maxDepth);
        } catch (GameStateException ex) {
            recursingValue = null;
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, "Brutus Test Game Crashed.", ex);
        }

        int returnMove;

        if (recursingValue.result.equals(PersonalResult.IWIN)) {
            log("Wining Path found, commencing with " + recursingValue.winnningMove, maxDepth, 5);
            returnMove = recursingValue.winnningMove;
        } else {
            //System.out.println("complete random is " + (int) (Math.random() * recursingValue.antiLoseMoves.size()));
            if (recursingValue.antiLoseMoves.isEmpty()) {
                log("ooops I lost", maxDepth, 3);
                
                //dont give em error
                returnMove = testGame.getEligetebleMoves().get(0);
            } else {
                returnMove = recursingValue.antiLoseMoves.get((int) (Math.random() * 1.0 * recursingValue.antiLoseMoves.size()));
                log("go for random move " + returnMove, maxDepth, 4);
            }
        }

        try {
            System.out.println(recursingValue.antiLoseMoves.toString());
            testGame.move(returnMove);
            inputListener.inputGiven(returnMove);
        } catch (InvalidMoveException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, "attempted to return " + returnMove, ex);
        } catch (GameStateException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, "attempted to return " + returnMove, ex);
        }
    }

    private PseudoSpiel4G doGhostMove(GameState shadowState, int col) throws GameStateException {
        PseudoSpiel4G bsGame = new PseudoSpiel4G();
        bsGame.playNewGame();
        bsGame.setGameState(shadowState);
        //boolean test = true; remember what we are here for..
        try {
            bsGame.move(col);
            //VierGewinntSpiel game = new VierGewinntSpiel(gameParticipants);
        } catch (InvalidMoveException ex) {
            //System.err.println("Top reached");

            bsGame = null; // yes this is questionable, if you need to change it, put this exact try and catch with a boolean back to where it came from
            // DO NOT optimize anything prematurely here, please
            // you're not a hero.
        } catch (GameStateException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
            bsGame = null;
        }
        return bsGame;
    }

    protected InvestigationResult investigateMove(GameState state, int depth) throws GameStateException {
        //depth--;
        ArrayList<Integer> notLosingMoves = new ArrayList<>();
        ArrayList<Pair<GameState, Integer>> unkownGameStates = new ArrayList<>();
        PersonalResult specificMoveResult = PersonalResult.GAMEGOESON;
        int highestGameDepth = 0;

        int winningMove = -1;
        for (int col = 0; col < state.SpielFeld.length; col++) {
            log("i move " + col, depth, 1);
            PseudoSpiel4G bsGame
                    = doGhostMove(state, col);
            if (bsGame != null) {
                notLosingMoves.add(col);

                if (iWon(bsGame.currentGameStatus)) {
                    winningMove = col;
                    specificMoveResult = PersonalResult.IWIN;
                    highestGameDepth = depth;
                    log("I win with " + col, depth, 2);
                    break;
                } else if (bsGame.currentGameStatus == GameResult.GameStillProgressing) {
                    if (depth > 0) {
                        unkownGameStates.add(new Pair<>(bsGame.getGameState(), col));
                    }
                }
            }
        }
        if (!specificMoveResult.equals(PersonalResult.IWIN)) {
            for (Pair<GameState, Integer> unkownGameState : unkownGameStates) {
                InvestigationResult invResult = investigateEnemyMove((GameState) unkownGameState.getKey(), depth - 1);

                switch (invResult.result) {
                    case ILOSE:
                    case DRAW:
                        //log("remove move " + col + " not winnable", depth, 1);
                        notLosingMoves.remove(unkownGameState.getValue());
                        break;
                    case IWIN:
                        if (invResult.depth > highestGameDepth) { // ?
                            highestGameDepth = invResult.depth;// ?
                            specificMoveResult = PersonalResult.IWIN;
                            winningMove = unkownGameState.getValue();
                            log("Gotta Win With " + unkownGameState.getValue(), depth, 2);
                        }
                    case GAMEGOESON:
                        log("Game goes on", depth, 1);
                        break;
                    default:
                        System.err.println("uncaught case error");
                }
            }
        }

        InvestigationResult val;
        if (notLosingMoves.isEmpty()) /* if(gamResult != PersonalResult.IWIN)*/ {
            val = new InvestigationResult(PersonalResult.ILOSE, highestGameDepth, notLosingMoves, winningMove);
        } else {
            val = new InvestigationResult(specificMoveResult, highestGameDepth, notLosingMoves, winningMove);
        }
        return val;
    }

    protected InvestigationResult investigateEnemyMove(GameState state, int depth) throws GameStateException {
        //depth--;
        PersonalResult gameResult = PersonalResult.GAMEGOESON;
        int highestWinDepth = 0;
        ArrayList<GameState> unkownGameStates = new ArrayList<>();

        for (int col = 0; col < state.SpielFeld.length; col++) {
            PseudoSpiel4G bsGame
                    = doGhostMove(state, col);
            if (bsGame != null) {
                if (bsGame.currentGameStatus == GameResult.GameStillProgressing) {
                    unkownGameStates.add(bsGame.getGameState());
                } else if (!iWon(bsGame.currentGameStatus)) { //-niet
                    gameResult = PersonalResult.ILOSE;
                    log("I bims 1 bigz nubz " + col, depth, 2);
                    break;
                } else {
                    // I can't possibly win after the enemy did a turn.
                    // This if case should never occur.
                    System.err.println("You found me");
                }
            }
        }
        boolean completeVictory = false;
        if (depth > 0) {
            if (gameResult == PersonalResult.GAMEGOESON) {
                completeVictory = true;
                for (int col = 0; col < unkownGameStates.size(); col++) {
                    //log("enemy Move" + col, depth, 1);
                    InvestigationResult val = investigateMove(unkownGameStates.get(col), depth - 1);
                    switch (val.result) {
                        case ILOSE:
                        case DRAW:
                            gameResult = val.result;
                            completeVictory = false; //originally not included 
                            //log("enemy wins with " + col, depth, 2);//also shouldnt i write complete Victory false herre??
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
        if (completeVictory) {
            log("complete Victory", depth, 3);
            gameResult = PersonalResult.IWIN;
        }
        InvestigationResult valu = new InvestigationResult(
                gameResult, highestWinDepth, null, 0);
        return valu;
    }

    private boolean iWon(GameResult res) {
        return amPlayer1 ? res.equals(GameWonForPlayer1) : res.equals(GameWonForPlayer2);
    }

    protected void log(String message, int depth, int worth) {
        if (worth > 2) {
            String tabs = "";
            for (int tab = 0; tab < maxDepth - depth; tab++) {
                tabs += "|\t";
            }
            System.out.println(tabs + message);
        }
    }

    InputListener<Integer> inputListener;

    @Override
    public void gameStarted(Game gameRef, InputListener<Integer> inputListener, boolean youHaveFirstMove) {
        amPlayer1 = youHaveFirstMove;
        this.inputListener = inputListener;
        try {
            testGame.playNewGame();
        } catch (GameStateException ex) {
            Logger.getLogger(Brutus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class InvestigationResult {

        public PersonalResult result;
        public int depth;
        public ArrayList<Integer> antiLoseMoves = new ArrayList<>();
        public int winnningMove;

        public ArrayList<Prediction> winningPredicitions = new ArrayList<>();
        public ArrayList<Prediction> losingPredicitions = new ArrayList<>();

        public InvestigationResult(PersonalResult result, int depth, ArrayList<Integer> antiLoseMoves, int winningMoves) {
            this.result = result;
            this.depth = depth;
            this.antiLoseMoves = antiLoseMoves;
            this.winnningMove = winningMoves;
        }

        public InvestigationResult() {

        }
    }

    class Prediction {

        public PersonalResult result;
        public ArrayList<Integer> alternatingMoves = new ArrayList<>();
    }
}
