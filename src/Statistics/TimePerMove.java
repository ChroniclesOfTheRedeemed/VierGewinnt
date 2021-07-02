/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Statistics;

import Interfaces.Game;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class TimePerMove extends Statistic {

    private long timeStamp;
    private Game gameRef;
    private long moves =0;
    
    public TimePerMove(String player1, String player2) {
        super(player1, player2);
    }

    @Override
    protected String getTitle() {
        return "Time Per Move of " + player1Name + " vs " + player2Name;
    }

    @Override
    protected String getCategoryAxisLabel() {
         return "Move";
    }

    @Override
    protected String getValueAxisLabel() {
        return "Time in ms";
    }

    @Override
    public void gameStarted(Game gameRef) {
        this.gameRef = gameRef;
        timeStamp = System.currentTimeMillis();
    }

    @Override
    public void moveSet(Object move) {
        if(gameRef.movesDone() % 2 == (Game.Player1hasFirstMove ? 1 : 0)){
            data.addValue(System.currentTimeMillis()-timeStamp, player1Name, "" + moves);
        }else{
            data.addValue(System.currentTimeMillis()-timeStamp, player2Name, "" + moves);
            moves++;
        }
        timeStamp = System.currentTimeMillis();
    }

    @Override
    public void gameEnded(Object finishingMove, Game.GameResult gameResult) {
    }

}
