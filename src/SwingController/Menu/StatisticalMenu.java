/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingController.Menu;

import Enums.StatisticEnum;
import Statistics.Statistic;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class StatisticalMenu extends JMenu {
    
    private boolean[] selectionStatistics;
    
    public StatisticalMenu() {
        super("Statistics");
        setupMenu();
    }
    
    public ArrayList<Statistic> createStatistics(String player1, String player2) {
        ArrayList<Statistic> result = new ArrayList<>();
        for (int i = 0; i < selectionStatistics.length; i++) {
            if (selectionStatistics[i]) {
                result.add(createSpecificStatistic(i, player1, player2));
            }
        }
        return result;
    }
    
    private void setupMenu() {
        this.add(statisticMenu());
    }
    
    private JMenu statisticMenu() {
        JMenu statsticMenu = new JMenu("Select Statistics");
        ArrayList<JCheckBoxMenuItem> choosableStatistics = new ArrayList<>();
        selectionStatistics = new boolean[StatisticEnum.values().length];
        
        for (int i = 0; i < StatisticEnum.values().length; i++) {
            final int leckMich = i;
            choosableStatistics.add(new JCheckBoxMenuItem(StatisticEnum.values()[i].name()));
            selectionStatistics[i] = choosableStatistics.get(i).isSelected();
            
            choosableStatistics.get(i).addActionListener((ActionEvent e) -> {
                selectionStatistics[leckMich] = choosableStatistics.get(leckMich).isSelected();
            });
            statsticMenu.add(choosableStatistics.get(i));
        }
        return statsticMenu;
    }
    
    private Statistic createSpecificStatistic(int nr, String player1, String player2) {
        return StatisticEnum.values()[nr].statFactory.createStatistic(player1, player2);
    }
}
