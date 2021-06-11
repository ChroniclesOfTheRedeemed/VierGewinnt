/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses;

import OtherStuff.Resource;
import OtherStuff.VillagerActivities;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.JDialog;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import simplebuildaoo.Event;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;
import simplebuildaoo.gameclasses.buildingStuff.Building;

/**
 *
 * @author absea
 */
public class InGameOverview {

    public Resource currentResources = new Resource();
    public ArrayList<Building> buildings = new ArrayList<>();
    public ArrayList<Unit> units = new ArrayList<>();
    public ArrayList<Villager> collectingVillagers = new ArrayList<>();

    public ArrayList<Villager> freeVils = new ArrayList();
    public ArrayList<Villager> buildingVils = new ArrayList();
    public ArrayList<Villager> walkingVills = new ArrayList();
    //public ArrayList<Villager> woodVils = new ArrayList();
    //public ArrayList<Villager> shephardVils = new ArrayList();
    //public ArrayList<Villager> boarHunterVils = new ArrayList();
    //public ArrayList<Villager> deerHunterVils = new ArrayList();
    
    //public ArrayList<Villager> farmerVils = new ArrayList();
    //public ArrayList<Villager> gathererVils = new ArrayList();
   // public ArrayList<Villager> stoneVils = new ArrayList();
    //public ArrayList<Villager> goldVils = new ArrayList();

    public Resource resourcePerSecond = new Resource();

    public ArrayList<Event> events = new ArrayList<>();

    public Building getBuilding(String name) {
        for (Building building : buildings) {
            if (building.getClass().getName().equals(name)) {
                return building;
            }
        }
        return null;
    }

    
    public ArrayList<Building> getBuildings(String name){
        ArrayList<Building> result = new ArrayList();
        buildings.stream().filter(building -> (building.getClass().getName().equals(name))).forEachOrdered(building -> {
            result.add(building);
        });
        return result;
    }

    public Unit getUnit(String name) {
        for (Unit unit : units) {
            if (unit.getClass().getName().equals(name)) {
                return unit;
            }
        }
        return null;
    }
    
    
    public ArrayList<Unit> getUnits(String name) {
        ArrayList<Unit> result = new ArrayList();
        units.stream().filter(building -> (building.getClass().getName().equals(name))).forEachOrdered(building -> {
            result.add(building);
        });
        return result;
    }
    
    public void updateEvent(Event previous, Event now){
        int index = this.events.indexOf(previous);
        events.set(index, now);
    }
    
    public void waitUp(int seconds) {
        for (int i = 0; i < seconds; i++) {
            for (int j = 0; j < events.size(); j++) {
                if(events.get(j).gameTime <= currentResources.time){
                    events.get(j).method.accept(null);
                    events.remove(j);
                    j--;
                }
            }
            
            
            
            woodLine.addValue(currentResources.wood, "Wood", Double.valueOf(currentResources.time));
            foodLine.addValue(currentResources.food, "Food", Double.valueOf(currentResources.time));
            stoneLine.addValue(currentResources.stone, "Stone", Double.valueOf(currentResources.time));
            goldLine.addValue(currentResources.gold, "Gold", Double.valueOf(currentResources.time));
            wait(1);
        }
    }
    
    public void wait(int seconds) {
        currentResources.food += this.resourcePerSecond.food * seconds;
        currentResources.wood += this.resourcePerSecond.wood * seconds;
        currentResources.stone += this.resourcePerSecond.stone * seconds;
        currentResources.gold += this.resourcePerSecond.gold * seconds;
        currentResources.time += seconds;
    }
    
    public static DefaultCategoryDataset woodLine = new DefaultCategoryDataset();
    public static DefaultCategoryDataset foodLine = new DefaultCategoryDataset();
    public static DefaultCategoryDataset stoneLine = new DefaultCategoryDataset();
    public static DefaultCategoryDataset goldLine = new DefaultCategoryDataset();

    public static void dos() {
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
    
}
