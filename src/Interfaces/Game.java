/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Exceptions.GameStateException;

/**
 *
 * @author Mike
 */
public interface Game {
    
    //Constants
    public int getSpielFeldBreite();
    public int getSpielFeldHöhe();
    public static final boolean Player1hasFirstMove = true;

    public static enum GameResult {
        GameWonForPlayer1, GameWonForPlayer2, Draw, GameStillProgressing
    }

    public String getNameOfPlayer(boolean player1);
    /* Returns the amount of valid moves that have
     * been made in the current game.
     * 
     * Since this is just a function for reference and can easily be simulated by the game participants it
     * will likely be discarded somewhere in the future.
     * Please keep that in mind when utilizing this function.
     * 
     * @return 
     */
    public int movesDone();

    /** Can be called anytime and advises the Game Instance to check upon the Player who should 
     * make a move. If a move has indeed been made, the Game Instance will commence the game.
     * This Function has to be directly or indirectly called by the player when he made his move,
     * because else the game will not progress.
     *
     * @throws GameStateException
     * @throws InvalidMoveException
     * @throws MoveNotAvailableException 
     */
   // public void playerMadeMove() throws GameStateException, InvalidMoveException;

    
    /** Initiates a new game and will progress in doing so until the game ends.
     * Game particioipants will be notified of the events that happpen during the game.
     * 
     * @throws GameStateException 
     */
    public void playNewGame() throws GameStateException;
}
