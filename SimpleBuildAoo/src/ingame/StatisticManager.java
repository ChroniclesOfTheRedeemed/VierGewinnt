/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.currentResources.
 */

package ingame;

import javax.swing.JDialog;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import simplebuildaoo.gameclasses.InGameOverview;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class StatisticManager {

    public static DefaultCategoryDataset woodLine = new DefaultCategoryDataset();
    public static DefaultCategoryDataset foodLine = new DefaultCategoryDataset();
    public static DefaultCategoryDataset stoneLine = new DefaultCategoryDataset();
    public static DefaultCategoryDataset goldLine = new DefaultCategoryDataset();
    public static DefaultCategoryDataset popLimit = new DefaultCategoryDataset();
    public static DefaultCategoryDataset builderLine = new DefaultCategoryDataset();
    public static DefaultCategoryDataset resourcesLine = new DefaultCategoryDataset();

    public static void doStats(InGameOverview r) {
        doStats(r.resman);
        doStats(r.vilman, r.resman.currentResources.time);
        builderLine.addValue(r.units.size(), "total", Double.valueOf(r.resman.currentResources.time));
    }

    public static void doStats(ResourceManager r) {
        
        woodLine.addValue(r.currentResources.wood, "Wood", Double.valueOf(r.currentResources.time));
        foodLine.addValue(r.currentResources.food, "Food", Double.valueOf(r.currentResources.time));
        stoneLine.addValue(r.currentResources.stone, "Stone", Double.valueOf(r.currentResources.time));
        goldLine.addValue(r.currentResources.gold, "Gold", Double.valueOf(r.currentResources.time));
        popLimit.addValue(r.currentResources.popLimit, "current Pop", Double.valueOf(r.currentResources.time));
        popLimit.addValue(r.totalPop, "total Poplimit", Double.valueOf(r.currentResources.time));
    }

    public static void doStats(VillagerManager vilman, double time) {
        builderLine.addValue(vilman.buildingVils.size(), "Builder", Double.valueOf(time));
        builderLine.addValue(vilman.collectingVillagers.size(), "Collectors", Double.valueOf(time));
        builderLine.addValue(vilman.freeVils.size(), "Freewills", Double.valueOf(time));
        builderLine.addValue(vilman.walkingVills.size(), "Walkers", Double.valueOf(time));
    }

    public static void dos() {
        {
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Builder",
                    "Count", "Wood",
                    woodLine,
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            JDialog x1 = new JDialog();
            x1.setContentPane(chartPanel);
            x1.pack();
            x1.setVisible(true);

            JFreeChart lineChart2 = ChartFactory.createLineChart(
                    "Builder",
                    "Count", "Food",
                    foodLine,
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel2 = new ChartPanel(lineChart2);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            JDialog x2 = new JDialog();
            x2.setContentPane(chartPanel2);
            x2.pack();
            x2.setVisible(true);

            JFreeChart lineChart3 = ChartFactory.createLineChart(
                    "Builder",
                    "Count", "Stone",
                    stoneLine,
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel3 = new ChartPanel(lineChart3);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            JDialog x3 = new JDialog();
            x3.setContentPane(chartPanel3);
            x3.pack();
            x3.setVisible(true);

            JFreeChart lineChart4 = ChartFactory.createLineChart(
                    "Builder",
                    "Count", "Gold",
                    goldLine,
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel4 = new ChartPanel(lineChart4);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            JDialog x4 = new JDialog();
            x4.setContentPane(chartPanel4);
            x4.pack();
            x4.setVisible(true);
        }
        {
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Popimit",
                    "Count", "pop",
                    popLimit,
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            JDialog x1 = new JDialog();
            x1.setContentPane(chartPanel);
            x1.pack();
            x1.setVisible(true);

        }
        {
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Tasks",
                    "Count", "tasks",
                    builderLine,
                    PlotOrientation.VERTICAL,
                    true, true, false);

            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
            JDialog x1 = new JDialog();
            x1.setContentPane(chartPanel);
            x1.pack();
            x1.setVisible(true);
        }
    }
}
