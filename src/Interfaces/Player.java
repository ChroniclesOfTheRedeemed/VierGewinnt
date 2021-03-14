/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Exceptions.MoveNotAvailableException;
import Interfaces.Game.GameResult;

/**
 *
 * @author Mike
 */
/**
 * the player HAS TO notify the game that he made his move.
 * The player should only take resources when he is participating in a game.
 * Due to the nature of the gaming session the player will stay instanciated the from start to finish, even when he's not playing
 * and should litter the environment as little as possible.
 * @author Mike
 * @param <Zug>
 */

    //always answer with an "playermademove" at the very end of this mademove function!
    //this function design is also the reason why gameEnded has the finishing move instead of the move beeing passed "normally"

public interface Player <Zug> {

    /**When a game starts where this player participates this player will be notified 
     * and given a reference to the game he participates in.
     * 
     * @param gameRef 
     * @param inputListener 
     * @param moveListener 
     */
    void gameStarted(Game gameRef, InputListener<Zug> inputListener, boolean youHaveFirstMove);

    /**The Player will be notified of the move of the enemy and is asked to calculate his next move now.
     * After the calculation are complete and a Move is available for further progression of the game
     * the method <playerMadeMove> of the Game reference is meant to be called.
     * 
     * @param enemyMove 
     */
    void makeMove(Zug enemyMove);


    /**The Player will be notified that the game ended, as well as what the last Move was and
     * how the game counts the result.
     * 
     * @param finishingMove
     * @param gameResult 
     */
    void gameEnded(Zug finishingMove, GameResult gameResult);
}
