/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Wird sich wahrscheinlich durchsetzen, da mehr auf AI Dauersessions gesetzt wird als auf einzelne Partien
//HashSet /HashMap / LinkedList implementieren
package Games.VierGewinnt;

import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import Exceptions.MoveNotAvailableException;
import Interfaces.Game;
import Interfaces.GameWatcher;
import java.util.ArrayList;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class Replay implements Game {

    private final ArrayList<GameWatcher> spectators = new ArrayList<>();
    private final ArrayList<GameData> gameData;
    private boolean gameInProgress = false;
    private int currentlyDisplayedGameNr;
    private int movesDone = 0;

    public Replay(ArrayList<GameData> newGameData) { //ArrayList won't be altered, no recoil to be considered
        gameData = new ArrayList(newGameData);
    }

    public ArrayList<GameWatcher> accessSpectators() {
        return spectators;
    }

    public int getCurrentGame() {
        return currentlyDisplayedGameNr;
    }

    public void setCurrentGame(int currentGameNr) throws Exception {
        if (currentGameNr >= 0 && currentGameNr < gameData.size()) {
            currentlyDisplayedGameNr = currentGameNr;
        } else {
            throw new Exception();
        }
    }

    public void playGameNr(int Nr) throws GameStateException {
        if (gameInProgress) {
            throw new GameStateException();
        } else {
            initiateGame();
            GameData crGame = gameData.get(Nr);
            playMoves(crGame);
            endGame(crGame.getMove(crGame.getTotalMoves()-1), gameData.get(Nr).result);
        }
    }

    /**
     * This method will now play all games stored in this replay session
     *
     * @throws GameStateException
     */
    @Override
    public void playNewGame() throws GameStateException {
        for (currentlyDisplayedGameNr = 0; currentlyDisplayedGameNr < gameData.size(); currentlyDisplayedGameNr++) {
            playGameNr(currentlyDisplayedGameNr);
        }
    }

    @Override
    public int movesDone() {
        return movesDone;
    }

    private void initiateGame() {
        movesDone = 0;
        gameInProgress = true;
        notifyGameStarted();
    }

    private void playMoves(GameData game) {
        for (int move = 0; move < game.getTotalMoves(); move++) {
            notifyMoveSet(game.getMove(move));
            movesDone++;
        }
    }
    private  void endGame(int move, GameResult result) {
        gameInProgress = false;
        notifyGameEnded(move, result);
    }

    //Array can be changed
    //functional operator do not provide this safety. I'd have to make a copy manually.
    private void notifyGameEnded(int spaltenNummer, GameResult result) {
        for (GameWatcher spectator : spectators) {
            spectator.gameEnded(spaltenNummer, result);
        }
    }

    private void notifyGameStarted() {
        for (GameWatcher spectator : spectators) {
            spectator.gameStarted(this);
        }
    }

    private void notifyMoveSet(int spaltenNummer) {
        for (GameWatcher spectator : spectators) {
            spectator.moveSet(spaltenNummer);
        }
    }

    @Override
    public String getNameOfPlayer(boolean player1) {
        if(player1){
            return gameData.get(currentlyDisplayedGameNr).Player1Name;
        } else {
            return gameData.get(currentlyDisplayedGameNr).Player2Name;
        }
    }

    @Override
    public int getSpielFeldBreite() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSpielFeldHöhe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
