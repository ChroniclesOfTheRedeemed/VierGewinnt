/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

import Interfaces.GameWatcher;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import util.EasyDialog;

/**
 *
 * @author Mike
 */
public abstract class Statistic implements GameWatcher {

    protected DefaultCategoryDataset data;
    protected EasyDialog mySocket;
    protected final String player1Name;
    protected final String player2Name;
    
    public Statistic(String player1, String player2){
        data = new DefaultCategoryDataset();
        if (player1.equals(player2)) {
            player1Name = player1 + " (1)";
            player2Name = player2 + " (2)";
        } else {
            player1Name = player1;
            player2Name = player2;
        }
        mySocket = getBasicStatSocket();
        mySocket.setVisible(true);
    }
   
    public final void setVisible(boolean visible) {
        mySocket.setVisible(visible);
    }

    public final boolean isVisible(){
        return mySocket.isVisible();
    }
    
    protected abstract String getTitle();

    protected abstract String getCategoryAxisLabel();

    protected abstract String getValueAxisLabel();

    private EasyDialog getBasicStatSocket() {
        return new EasyDialog(new ChartPanel(ChartFactory.createLineChart(
                getTitle(),
                getCategoryAxisLabel(), getValueAxisLabel(),
                data,
                PlotOrientation.VERTICAL,
                true, true, false)), /*Game.GameName +*/ " Statistic");
    }

}
