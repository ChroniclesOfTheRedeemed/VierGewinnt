/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Games.VierGewinnt;

import Exceptions.InvalidMoveException;
import Exceptions.GameStateException;
import Interfaces.Game;
import Interfaces.GameWatcher;
import Interfaces.InputListener;
import java.util.ArrayList;
import java.util.Objects;
import viergewinntpraxis.GameParticipants;

/**Erstellung am 30.09.18
 *
 * @author Mike
 */
public class VierGewinntSpiel implements Game{
    
    public static final int SpielFeldBreite = 7;
    public static final int SpielFeldHöhe = 6;
    private static final Boolean FeldLeer = null, Spieler1Markierung = false, Spieler2Markierung = true;   
    private static final int Four = 4;
    
    // Spiel ablaufsVariablen
    public final int MaxMoves; //realitätsgebunden, denn manchmal reichen die Chips einfach nicht für ein ganzes Spiel
    public static Integer FirstMove = -1;
    private int movesDone;
    private boolean player1turn;
    private Boolean[][] SpielFeld; //Coordinate element access  Implementation
    private GameResult currentGameStatus; 
    
    // Interfaces
    private final VierGewinntPlayer player1;
    private final VierGewinntPlayer player2;
    private final ArrayList<GameWatcher> spectators;
    
    
    public VierGewinntSpiel(int maxMoves, GameParticipants gameParticipants) {
        this.player1 = (VierGewinntPlayer) gameParticipants.player1;
        this.player2 = (VierGewinntPlayer) gameParticipants.player2;
        this.spectators = gameParticipants.spectators;
        this.MaxMoves = Math.min(maxMoves, SpielFeldBreite * SpielFeldHöhe);
    }

    public VierGewinntSpiel(GameParticipants gameParticipants) {
        this.player1 = (VierGewinntPlayer) gameParticipants.player1;
        this.player2 = (VierGewinntPlayer) gameParticipants.player2;
        this.spectators = gameParticipants.spectators;
        this.MaxMoves = SpielFeldBreite * SpielFeldHöhe;
    }
    
    /**Because of the current implementation, infinitely many games can be played (or inititiated, by the players or spectators)
     * 
     * @throws GameStateException 
     */
    @Override
    public void playNewGame() throws GameStateException {
        if (currentGameStatus != GameResult.GameStillProgressing) {
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
    
    private void playerMadeMove(int move) throws GameStateException, InvalidMoveException {
        
        // we will change this code, no more playermadeMoveStuff
        // I already did some changes, I really wonder if they'll show up 
        // at an unnice point
        if (currentGameStatus == GameResult.GameStillProgressing) {
            
            
            checkMove(move);  
            int levelOfMove = updateSpielFeld(move);//previous makeMoveOnSpielFeld
            
            updateGamestatus(move, levelOfMove);//honor to previous version with boolean gameEnded (it didn't specify that it'll update the gamestatus though)
      
            if (currentGameStatus != GameResult.GameStillProgressing) {
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
        SpielFeld = new Boolean[SpielFeldBreite][SpielFeldHöhe];
       // Arrays.fill(Arrays.fill(SpielFeld, this), FirstMove, four, this);
        player1turn = Player1hasFirstMove;
        currentGameStatus = GameResult.GameStillProgressing;
        movesDone = 0;
    }
  
    private void checkMove(int move) throws InvalidMoveException {
        if (move >= 0 && move < SpielFeldBreite) {
            if (!Objects.equals(SpielFeld[move][SpielFeldHöhe - 1], FeldLeer)) {
                throw new InvalidMoveException(move);
            }
        } else {
            throw new InvalidMoveException(move);
        }
    }
    
    //Considers game can possibly only end in victory for the moving player or in draw.
    //Should be very efficient because it is called for every move;
    private void updateGamestatus(int move, int levelOfMove) {
        if (gameWon(move, levelOfMove)) {
            if (Objects.equals(getMarkOfLastMove(move), Spieler2Markierung)) {//redundant to some degree
                currentGameStatus = GameResult.GameWonForPlayer2;
            } else {
                currentGameStatus = GameResult.GameWonForPlayer1;
            }
            //gameStillProgressing = false;
        } else {
            if (gameDraw()) {
                currentGameStatus = GameResult.Draw;
                //gameStillProgressing = false;
            }
        }
    }

    private boolean gameWon(int move, int level) {
        boolean result;
        Boolean Mark = getMarkOfLastMove(move);

        if (wonThroughVerticalConnect(move, level, Mark)) {
            result = true;
        } else {
            result = wonNotVertically(move, level, Mark);
        }
        return result;
    }

    
    
    private boolean wonThroughVerticalConnect(int move, int levelOfMove, Boolean Mark) {
        int connect = 1;
        for (int row = levelOfMove - 1; row >= 0; row--) {
            if (Objects.equals(SpielFeld[move][row], Mark)) {
                connect++;
            } else {
                break;
            }
        }
        //will be checked each turn, so as soon as 4 connect vertically, it will be instantly noticed.
        return connect == Four;
    }

    
    //uncheckedc
    private boolean wonNotVertically(int move, int level, Boolean Mark) {
        int connect;
        final int directions = 3;
        int coordinateC;
        int coordinateR;
        int increaseC;
        int increaseR;

        for (int direction = 0; direction < directions; direction++) {
            connect = 1;
            increaseC = 1;
            increaseR = direction - 1;
            coordinateC = move + increaseC;
            coordinateR = level + increaseR;

            for (int i = 0; i < Four-1; i++) {

                if (inBoundaries(coordinateC, coordinateR) && Objects.equals(SpielFeld[coordinateC][coordinateR], Mark)) {
                    connect++;
                    coordinateC += increaseC;
                    coordinateR += increaseR;
                } else {
                    break;
                }
            }
            if (connect == Four) {
                return true;
            }
            increaseC = -increaseC;
            increaseR = -increaseR;
            coordinateC = move + increaseC;
            coordinateR = level + increaseR;
            for (int i = 0; i < Four-1; i++) {

                if (inBoundaries(coordinateC, coordinateR) && Objects.equals(SpielFeld[coordinateC][coordinateR], Mark)) {
                    connect++;
                    coordinateC += increaseC;
                    coordinateR += increaseR;
                } else {
                    break;
                }
            }
            if (connect >= Four) {
                return true;
            }
        }
        return false;
    }

    //doesn't exclude that the game has been won, so it has to be treated carefully and always after gamewon has been specified
    private boolean gameDraw(){
        return movesDone == MaxMoves;
    }
    
    //should be ideally redundant, and destroyed
    private Boolean getMarkOfLastMove(int move){
        Boolean result = FeldLeer;
        for (int row = 0; row < SpielFeldHöhe; row++) {
            if (Objects.equals(SpielFeld[move][row], FeldLeer)) {
                break;
            } else {
                result = SpielFeld[move][row];
            }
        }
        return result;
    }
    
    private boolean inBoundaries(int Coloumn, int Row){
        return Coloumn >= 0 && Coloumn < SpielFeldBreite && Row >= 0 && Row < SpielFeldHöhe;
    }

    private int updateSpielFeld(int checkedMove) {
        Boolean Mark;
        //Mark = player1turn;
        if (player1turn) {
            Mark = Spieler1Markierung;
        } else {
            Mark = Spieler2Markierung;
        }
        int level;
        for (level = 0; level < SpielFeldHöhe; level++) {
            if (Objects.equals(SpielFeld[checkedMove][level], FeldLeer)) {
                SpielFeld[checkedMove][level] = Mark;
                break;
            }
        }
        movesDone++;
        player1turn = !player1turn;
        return level;
    }
    
    private void notifyGameEnded(int spaltenNummer) {
        player1.gameEnded(spaltenNummer, currentGameStatus);
        player2.gameEnded(spaltenNummer, currentGameStatus);
        for (GameWatcher spectator : spectators) { // I hope order will be kept
            spectator.gameEnded(spaltenNummer, currentGameStatus);
        }
    }

    private void notifyGameStarted() {
        InputListener<Integer> player1moveListener = (Integer validMove) -> {
            if(player1turn){
                playerMadeMove(validMove);
            } else {
                throw new GameStateException();
            }
        };
        InputListener<Integer> player2moveListener = (Integer validMove) -> {
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

    private void notifyMoveSet(int spaltenNummer) {
    //if player wants to know enemy move sooner he can just implement himself as spectator, it's just not necessary to let the spectators wait until the player found  counter move
    //to the move they don't even yet know of

    //therefore of course the spectator notify function should be as fast as possible
    
    
    // threading would completely annihilate the above text, nothing will be changed though because:
    // of the following:
    // now it comes:
    // When the player gets the ball, everything in this class has to be clean and cleaned up BEFORE 
    // the player returns the ball
    
    // for example (since I dont thread spectators), one spectator suddenly takes 5 seconds, player 2 done it's move and playerMadeMove executes.
    //  now spectator that still runs is executed twice (not my problem I guess), but if he's then suddenly faster, the next spectator gets the second move before the first
    //  und so on, bugs bound to appear everywhere, where one is written
    
    // else (sure written a lot already, phew) I'd have to make sure that both cases are done before I start the next playerMadeMove:
    //  player x has done his next move
    //  all spectators have gotten their numbers.
    
    // why care so much for spectators? Because I'm not heartless (:
    
        for (GameWatcher spectator : spectators) {
            spectator.moveSet(spaltenNummer);
        }
        if (Objects.equals(getMarkOfLastMove(spaltenNummer), Spieler2Markierung)) {
            (new Thread(() -> player1.makeMove(spaltenNummer))).start();
        } else {
            (new Thread(() -> player2.makeMove(spaltenNummer))).start();
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