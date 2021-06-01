/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package VierGewinntPlayers;

import Interfaces.Game;
import Interfaces.InputListener;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class PseudoVierGewinntPlayer implements Games.VierGewinnt.VierGewinntPlayer {

    @Override
    public void gameStarted(Game gameRef, InputListener<Integer> inputListener, boolean youHaveFirstMove) {
        System.out.println("Game started for random dude, should not happen ideally.");
    }

    @Override
    public void makeMove(Integer enemyMove) {
        System.out.println("Game started for random dude, should not happen ideally.");
    }

    @Override
    public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
        System.out.println("Game started for random dude, should not happen ideally.");
    }

}
