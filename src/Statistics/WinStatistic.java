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
public class WinStatistic extends Statistic {

    private int wins = 0;
    private int games = 0;
    
    public WinStatistic(String player1, String player2) {
        super(player1, player2);
    }

    @Override
    protected String getTitle() {
        return "Win Ratio of " + player1Name + " vs " + player2Name;
    }

    @Override
    protected String getCategoryAxisLabel() {
         return "Games";
    }

    @Override
    protected String getValueAxisLabel() {
        return "Win Ratio";
    }

    @Override
    public void gameStarted(Game gameRef) {
        setVisible(true);
    }

    @Override
    public void moveSet(Object move) {
        
    }

    @Override
    public void gameEnded(Object finishingMove, Game.GameResult gameResult) {
        switch (gameResult) {
            case GameWonForPlayer1:
                wins++;
                break;
        }
        games++;
        data.addValue(wins * 100 / games, "Ratio", "" + games);
    }

}
