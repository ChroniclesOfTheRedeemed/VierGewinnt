/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses;

import java.util.ArrayList;
import simplebuildaoo.Event;
import ingame.ResourceManager;
import ingame.StatisticManager;
import ingame.VillagerManager;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.buildingStuff.Building;

/**
 *
 * @author absea
 */
public class InGameOverview {

    //Event Manager
    // Building Manager
    public VillagerManager vilman = new VillagerManager();
    public ResourceManager resman = new ResourceManager();
    public StatisticManager statMan = new StatisticManager();

    public ArrayList<Event> events = new ArrayList<>();

    public ArrayList<Building> buildings = new ArrayList<>();
    public ArrayList<Unit> units = new ArrayList<>();
    //public ArrayList<Villager> woodVils = new ArrayList();
    //public ArrayList<Villager> shephardVils = new ArrayList();
    //public ArrayList<Villager> boarHunterVils = new ArrayList();
    //public ArrayList<Villager> deerHunterVils = new ArrayList();
    //public ArrayList<Villager> farmerVils = new ArrayList();
    //public ArrayList<Villager> gathererVils = new ArrayList();
    // public ArrayList<Villager> stoneVils = new ArrayList();
    //public ArrayList<Villager> goldVils = new ArrayList();

    public InGameOverview(TechTreeSheet CTS) {
        vilman.CTS = CTS;
        vilman.IGO = this;
    }

    public Building getBuilding(String name) {
        for (Building building : buildings) {
            if (building.getClass().getName().equals(name)) {
                return building;
            }
        }
        return null;
    }

    public ArrayList<Building> getBuildings(String name) {
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

    public void updateEvent(Event previous, Event now) {
        int index = this.events.indexOf(previous);
        events.set(index, now);
    }

    public void waitUp(int seconds) {
        for (int i = 0; i < seconds; i++) {
            for (int j = 0; j < events.size(); j++) {
                if (events.get(j).gameTime <= resman.currentResources.time) {
                    events.get(j).method.accept(null);
                    events.remove(j);
                    j--;
                }
            }
            StatisticManager.doStats(this);
            resman.wait(1);
        }
    }

}
