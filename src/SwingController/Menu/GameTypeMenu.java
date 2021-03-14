/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingController.Menu;

import Statistics.Statistic;
import Statistics.TimePerMove;
import Statistics.TimePerMoveNano;
import Statistics.WinStatistic;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import Enums.GameType;
import java.awt.event.ActionListener;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class GameTypeMenu extends JMenu {

    public GameTypeMenu(GameChangedListener gameChangedListener) {
        super("Games");
        setupMenu(gameChangedListener);
    }

    private void setupMenu(GameChangedListener gameChangedListener) {
        ButtonGroup Radio = new ButtonGroup();
        for (GameType value : GameType.values()) {
            JRadioButtonMenuItem newItem = new JRadioButtonMenuItem(value.toString());
            Radio.add(newItem);
            newItem.addActionListener((ActionEvent e) -> {
                gameChangedListener.gameChanged(value);
            });

            this.add(newItem);
        }
    }

}
