/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SwingController.Menu;

import Interfaces.GameWatcher;
import Statistics.Statistic;
import java.util.ArrayList;
import javax.swing.JMenuBar;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class ControlMenuBar extends JMenuBar {
    
    StatisticalMenu statMenu;
    OptionsMenu optMenu;
    GameTypeMenu gameMenu;

    public ControlMenuBar(ArrayList<GameWatcher> spectators, GameChangedListener gameChangedListener ) {
        setupMenu(spectators, gameChangedListener);
    }

    public boolean shouldRecord() {
        return optMenu.shouldRecord();
    }

    public ArrayList<GameWatcher> getSelectedSpectators() {
        return optMenu.getSelectedSpectators();
    }

    public ArrayList<Statistic> createStatistics(String player1, String player2) {
        return statMenu.createStatistics(player1, player2);
    }
    
    private void setupMenu(ArrayList<GameWatcher> spectators, GameChangedListener gameChangedListener){
        optMenu = new OptionsMenu(spectators);
        statMenu = new StatisticalMenu();
        gameMenu = new GameTypeMenu(gameChangedListener);
        this.add(optMenu);
        this.add(statMenu);
        this.add(gameMenu);
    }
}




