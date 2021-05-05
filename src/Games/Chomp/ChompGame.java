/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Games.Chomp;

import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import Interfaces.Game;
import static Interfaces.Game.Player1hasFirstMove;
import Interfaces.GameWatcher;
import Interfaces.InputListener;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Objects;
import viergewinntpraxis.GameParticipants;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */

/**Erstellung am 30.09.18
 *
 * @author Mike
 */
public class ChompGame implements Game{
    
    public static final int SpielFeldBreite = 6;
    public static final int SpielFeldHöhe = 4;
    public static final Dimension FirstMove = null;
    private static final boolean FeldLeer = false, FeldBelegt = true;
    
    //Spiel ablaufsVariablen
    private int movesDone;
    private boolean player1turn;
    private boolean[][] SpielFeld; //Coordinate element access  Implementation
    private boolean gameStillProgressing = false;
    private Game.GameResult lastGameResult; 
    
    //Interfaces
    private final ChompPlayer player1;
    private final ChompPlayer player2;
    private final ArrayList<GameWatcher> spectators;
    
    public ChompGame(int maxMoves, GameParticipants gameParticipants) {
        this.player1 = (ChompPlayer) gameParticipants.player1;
        this.player2 = (ChompPlayer) gameParticipants.player2;
        this.spectators = gameParticipants.spectators;
    }

    public ChompGame(GameParticipants gameParticipants) {
        this.player1 = (ChompPlayer) gameParticipants.player1;
        this.player2 = (ChompPlayer) gameParticipants.player2;
        this.spectators = gameParticipants.spectators;
    }
    
    /**Because of the current implementation, infinitely many games can be played (or inititiated, by the players or spectators)
     * 
     * @throws GameStateException 
     */
    @Override
    public void playNewGame() throws GameStateException {
        if (!gameStillProgressing/*currentGameStatus != GameResult.GameInProgress*/) {
            initializeVariables();
            notifyGameStarted();
            if (Player1hasFirstMove) {
                player1.makeMove(FirstMove);
            } else {
                player2.makeMove(FirstMove);
            }
        } else {
            throw new GameStateException();
        }
    }

    private void playerMadeMove(Dimension move) throws GameStateException, InvalidMoveException {
        if (gameStillProgressing) {
            
            checkMove(move);  
            updateSpielFeld(move);//previous makeMoveOnSpielFeld
            
            updateGamestatus(move);//honor to previous version with boolean gameEnded (it didn't specify that it'll update the gamestatus though)
            player1turn = !player1turn;
            movesDone++;
            if (!gameStillProgressing) {
                notifyGameEnded(move);
            } else {
                notifyMoveSet(move);
            }
        } else {
            throw new GameStateException();
        }
    }
    
    /** Technically redundant, but might still stay awhile
     * returns the amount of moves that have been done this game
     * 
     * @return 
     */
    @Override
    public int movesDone(){
        return movesDone;
    }

    private void initializeVariables(){
        SpielFeld = new boolean[SpielFeldBreite][SpielFeldHöhe];
        player1turn = Player1hasFirstMove;
        gameStillProgressing = true;
        movesDone = 0;
    }
  
    private void checkMove(Dimension move) throws InvalidMoveException {
        if (move.width >= 0 && move.width < SpielFeldBreite) {
            if (move.height >= 0 && move.height < SpielFeldHöhe) {
                if (!Objects.equals(SpielFeld[move.width][move.height], FeldLeer)) {
                    throw new InvalidMoveException(move.height);
                }
            } else {
                throw new InvalidMoveException(move.height);
            }
        } else {
            throw new InvalidMoveException(move.height);
        }
    }
    
    //Considers game can possibly only end in victory for the moving player or in draw.
    //Should be very efficient because it is called for every move;
    private void updateGamestatus(Dimension move) {
        if (gameWon(move)) {
            if (player1turn) {//redundant to some degree
                lastGameResult = Game.GameResult.GameWonForPlayer2;
            } else {
                lastGameResult = Game.GameResult.GameWonForPlayer1;
            }
            gameStillProgressing = false;
        }
    }

    private boolean gameWon(Dimension move) {
        return(move.height == 0 && move.width == 0);
    }
    
    private void updateSpielFeld(Dimension checkedMove) {
        for (int width = checkedMove.width; width < SpielFeldBreite; width++) {
            if (SpielFeld[width][checkedMove.height] == FeldLeer ) {
                for (int height = checkedMove.height; height < SpielFeldHöhe; height++) {
                    if (SpielFeld[width][height] == FeldLeer) {
                        SpielFeld[width][height] = FeldBelegt;
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }
    
    private void notifyGameEnded(Dimension spaltenNummer) {
        player1.gameEnded(spaltenNummer, lastGameResult);
        player2.gameEnded(spaltenNummer, lastGameResult);
        for (GameWatcher spectator : spectators) { // I hope order will be kept
            spectator.gameEnded(spaltenNummer, lastGameResult);
        }
    }

    private void notifyGameStarted() {
        InputListener<Dimension> player1moveListener = (Dimension validMove) -> {
            if(player1turn){
                playerMadeMove(validMove);
            } else {
                throw new GameStateException();
            }
        };
        InputListener<Dimension> player2moveListener = (Dimension validMove) -> {
            if(!player1turn){
                playerMadeMove(validMove);
            } else {
                throw new GameStateException();
            }
        };
        player1.gameStarted(this, player1moveListener, true);
        player2.gameStarted(this, player2moveListener, false);
        //do not change to functional operation, because spectator can leave the queue during the loop
        for (GameWatcher spectator : spectators) {
            spectator.gameStarted(this);
        }
    }

    private void notifyMoveSet(Dimension spaltenNummer) {
    //if player wants to know enemy move sooner he can just implement himself as spectator, it's just not necessary to let the spectators wait until the player found  counter move
    //to the move they don't even yet know of

    //therefore of course the spectator notify function should be as fast as possible
        for (GameWatcher spectator : spectators) {
            spectator.moveSet(spaltenNummer);
        }
        if (player1turn) {
            player1.makeMove(spaltenNummer);
        } else {
            player2.makeMove(spaltenNummer);
        }
    }

    @Override
    public String getNameOfPlayer(boolean player1) {
        if (player1) {
            return this.player1.toString();
        } else {
            return this.player2.toString();
        }
    }

    @Override
    public int getSpielFeldBreite() {
        return SpielFeldBreite;
    }

    @Override
    public int getSpielFeldHöhe() {
        return SpielFeldHöhe;
    }
}



// to dos:
// specify exceptions
// check nonvertical gamewon
// detete comments
// crete javadoc

//to do s
//create additional Game Interface
//make new enum evtlually
//lots of clean up work and commentory etc

//FOr future work: Make the players know, what player they are/how the game looks at them.

/**Postcreation changes:
 *      07.03.19
 *      Key Variable GameState with 4 different states has been splitted into:
 *          2 state Boolean     gameInProgress
 *          3 state GameResult  thisGameResult
 *
 *      Contra: Newly introduced redundance in information (4 states have the same information as 5 states)
 *      Pro:    Abstraction looks cleaner
 * 
 *  Question; how can the interactor access the information if the game is in progress? if at all?
 *  (Simple) Answer: A game has started but not yet ended.
 *  BUT: obviously requires very clean pathing from the game class implementation
 * 

 * Q: Ist es realistisch mehr als ein Spiel gleichzeitig spielen können zu müssen?
 * A: Prinzipiell ja, konkret stellt das Player Interface aber nur einen Spieler dar, der EIN Spiel momentan spielt. Hat man vor mehrere Spiele zu spielen
 *      muss man das von der Spieler Seite realisieren, der <Spieler eines Spiels> ist nämlich weitestgehend anonym und ist daher auch nicht vorgesehen rumgereicht
 *      zu werden. Am besten stellt sich dann eine Spieler Klasse, die Einzelne Spieler für konkrete Spiele deployed, sollte dies explizit erwünscht sein.
                * Question:    How can the interactor access the information if the game is in progress when he is playing multiple games at once?
               * Answer:      The design of multiple games is not implicitly supported yet but,
               *                  First of all, the status quo is that a clone of the player plays one game at a time at max. Exception when he can handle playing against himself.
               *                  Which is (obviously) supported game wise
               * 
               * OQuestion:   Can one interactor AT ALL play multiple games at once?
               * Answer:      Only when he knows which games calles his respective function, which is as far as I know, not the case :(
               * 
               * Creator Question:    How could that be changed? (SPOILER: it shouldn't and won't)
               * Answer:              1. The API could be designed slightly more complex (maybe), like giving the interactor a new object
               *                      Hauptproblem ist beim Grundlegenden designen der Applikation wurde vom SPIEL als Center ausgegagngen, das Spiel als Mittelpunkt und Hauptattraktion, nicht der Spieler
               * :)
 * 
 * 
 */