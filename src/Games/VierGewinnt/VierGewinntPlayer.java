/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Games.VierGewinnt;


/**
 * the player HAS TO notify the game that he made his move.
 * The player should only take resources when he is participating in a game.
 * Due to the nature of the gaming session the player will stay instanciated the from start to finish, even when he's not playing
 * and should litter the environment as little as possible.
 * @author Mike
 */

    //always answer with an "playermademove" at the very end of this mademove function!
    //this function design is also the reason why gameEnded has the finishing move instead of the move beeing passed "normally"

public interface VierGewinntPlayer extends Interfaces.Player<Integer> {

}
