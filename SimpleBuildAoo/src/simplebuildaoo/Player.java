/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo;

import OtherStuff.Resource;
import OtherStuff.ResourceUnit;
import OtherStuff.VillagerActivities;
import OtherStuff.VillagerGatherableResource;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.JDialog;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import simplebuildaoo.gameclasses.Civ;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.TechTreeSheet;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;
import simplebuildaoo.gameclasses.buildingStuff.Building;
import simplebuildaoo.gameclasses.buildingStuff.BuildingFactory;

/**
 *
 * @author absea
 */
public class Player {

    public Civ completeBuildSheet; // (my civ + teamboni as parameter) init at game start, but can be manipulated by technologies

    public InGameOverview IGO; // Resources, Units, Building, Age, ?Technologies? 

    public ArrayList<Civ> AlliedCivs; // just for fun

    public TechTreeSheet CTS;

    public Player(ArrayList<Civ> AlliedCivs, Civ completeBuildSheet) {
        createMyself(AlliedCivs, completeBuildSheet);
    }

    private void createMyself(ArrayList<Civ> AlliedCivs, Civ completeBuildSheet) {
        CTS = new TechTreeSheet(AlliedCivs, completeBuildSheet);
        CTS.ownedBy = this;
        completeBuildSheet.CivBonus.overwriteTechTree(CTS);
        AlliedCivs.forEach(civ -> {
            civ.TeamBonus.overwriteTechTree(CTS);
        });

        IGO = new InGameOverview();
        IGO.currentResources = CTS.startResources;
        IGO.buildings.add(CTS.townCenterBuilder.createBuilding());
        IGO.units.add(CTS.VillagerFactory.createUnit());
        IGO.units.add(CTS.VillagerFactory.createUnit());
        IGO.units.add(CTS.VillagerFactory.createUnit());
        IGO.units.add(CTS.scoutFactory.createUnit());

        CTS.darkAge.overwriteTechTree(CTS);
        completeBuildSheet.darkAgePatch.overwriteTechTree(CTS);
        calculateResourcesPerSecond(IGO);

    }

    public void addResourcesForSecond() {
        IGO.currentResources.time += 0;
        IGO.currentResources.food += IGO.resourcePerSecond.food;
        IGO.currentResources.wood += IGO.resourcePerSecond.wood;
        IGO.currentResources.gold += IGO.resourcePerSecond.gold;
        IGO.currentResources.stone += IGO.resourcePerSecond.stone;

    }

    public void calculateResourcesPerSecond(InGameOverview InGameOverview) {
        Resource newe = new Resource();
        for (Villager vil : InGameOverview.collectingVillagers) {
            Resource news = CTS.getCollectionSpeedByResouceType(vil.currentlyCollecting);
            newe.food += news.food;
            newe.wood += news.wood;
            newe.gold += news.gold;
            newe.stone += news.stone;
        }

        InGameOverview.resourcePerSecond = newe;
    }

    private void reassignTaskonVillagers(VillagerActivities from, VillagerActivities to, int count) {

        for (int i = 0; i < count; i++) {
            if (this.IGO.units.get(i) instanceof Villager) { // to be optimized - extract Villager from other Units
                Villager vill = (Villager) this.IGO.units.get(i);
                if (vill.task.equals(from)) {
                    vill.task = to;
                    //vill.currentlyCollecting = to;

                }
            }
        }
    }

    public void fromTof(ArrayList<Villager> from, Building building, int count) {

    }

    public void reassignStuff(VillagerActivities from, VillagerActivities to, ResourceUnit source, int amount) {
        ArrayList<Villager> fromVils = getDoingAct(from, amount);
        ArrayList<Villager> toVils = getDoingAct(to, amount);
        for (int i = 0; i < amount && !fromVils.isEmpty(); i++) {
            Villager nes = fromVils.remove(0);
            nes.task = to;
            if (from.equals(VillagerActivities.COLLECTOR)) {
                IGO.collectingVillagers.remove(nes);
            }
            if (to.equals(VillagerActivities.COLLECTOR)) {
                IGO.collectingVillagers.add(nes);
                source.gatheres.add(nes);
                
                System.out.println("gatherer joined " + source.gatheres.size());  
            }
            nes.currentlyCollecting = source.meGather;
            toVils.add(nes);
        }
    }

    public void updateHoldingOnResourceUnit(ResourceUnit unit){
        double currentTime = this.IGO.currentResources.time;
        
        if(unit.lastlyUpdated == -1) {
        } else {
            double differenceInTime = currentTime - unit.lastlyUpdated;
            double depletionSpeedv2 = (unit.gatheres.size() * this.CTS.getCollectionSpeedByResouceType(unit.meGather).total()
                + unit.depletionSpeed.total() / unit.depletionSpeed.time);
            unit.currentHoldingResource.food = Math.max(unit.currentHoldingResource.food - differenceInTime * depletionSpeedv2, 0);
            unit.currentHoldingResource.wood = Math.max(unit.currentHoldingResource.wood - differenceInTime * depletionSpeedv2, 0);
            unit.currentHoldingResource.gold = Math.max(unit.currentHoldingResource.gold - differenceInTime * depletionSpeedv2, 0);
            unit.currentHoldingResource.stone = Math.max(unit.currentHoldingResource.stone - differenceInTime * depletionSpeedv2, 0);
            
        System.err.println("so networth is " + (300 - this.IGO.currentResources.food - unit.currentHoldingResource.food));
            //resourceleft = resource there - timepassed * food/time
        }
    }
    
    public void toThing(ResourceUnit unit, VillagerActivities from, int amount) {
        //Reassign all villager duties
        //reorganize Villager Arrays
        //recalculate resource speed

        if (unit.currentHoldingResource.total() > 0) {

            updateHoldingOnResourceUnit(unit);

            reassignStuff(from, VillagerActivities.COLLECTOR, unit, amount);

            double resources = unit.currentHoldingResource.total();
            double gatherSpeed = unit.gatheres.size() * this.CTS.getCollectionSpeedByResouceType(unit.meGather).total();
            double depletionSpeed = unit.depletionSpeed.total() / unit.depletionSpeed.time;
            int timeUntilDepletion = (int) (resources / (gatherSpeed + depletionSpeed));
            System.out.println("XXtime until depletion " + timeUntilDepletion);
            System.out.println("XXremaining resource = " + unit.currentHoldingResource.total());

            Consumer resourceDepletedEvent = (stuuf) -> {
                System.out.println("At me gathering are " + unit.gatheres.size());
                //free em up
                assert (unit.currentHoldingResource.total() < 1);
                unit.currentHoldingResource = new Resource();
                unit.gatheres.forEach((t) -> {
                    System.out.println("gatherer left");
                    t.task = VillagerActivities.NONE;
                    t.currentlyCollecting = VillagerGatherableResource.NONE;
                    this.IGO.collectingVillagers.remove(t);
                    this.IGO.freeVils.add(t);
                });
                unit.gatheres.clear();
                this.calculateResourcesPerSecond(IGO);

            };
            System.out.println("At me gathering are " + unit.gatheres.size());

            if (unit.lastlyUpdated == -1) {
                unit.depletionEvent = new Event((int) (IGO.currentResources.time + timeUntilDepletion), resourceDepletedEvent);
                this.IGO.events.add(unit.depletionEvent);
            } else {
                unit.depletionEvent.gameTime = (int) (IGO.currentResources.time + timeUntilDepletion);
                unit.depletionEvent.method = resourceDepletedEvent;
                //this.IGO.updateEvent(unit.depletionEvent, resourceDepletedEvent);
            }
            unit.lastlyUpdated = IGO.currentResources.time;
            this.calculateResourcesPerSecond(IGO);

        }
    }

    public void build(String buildingName, VillagerActivities from, int amount) {
        for (BuildingFactory build : CTS.vtmp.possibleBuildings) {
            if (build.getName().equals(buildingName)) {
                pay(build.getCost());
                Event buildEvent = new Event((int) (build.getCost().time + 0.5), (Consumer) (Object t) -> {
                });

            }

        }
    }

    public void pay(Resource res) {
        IGO.currentResources.food -= res.food;
        IGO.currentResources.wood -= res.wood;
        IGO.currentResources.stone -= res.stone;
        IGO.currentResources.gold -= res.gold;
        IGO.currentResources.popLimit -= res.popLimit;
    }

    public void toResourcge(ResourceUnit unit, VillagerGatherableResource from, int amount) {
        //reassignTaskonVillagers(from, VillagerGatherableResource.SHEEP, amount);
        ArrayList<Villager> result = getDoing(from, amount);
    }

    private ArrayList<Villager> getDoing(VillagerGatherableResource resource, int amount) {
        ArrayList<Villager> result = new ArrayList<>();
        for (Unit vil : IGO.units) {
            if (vil instanceof Villager) { // to be optimized - extract Villager from other Units
                Villager vill = (Villager) vil;
                if (vill.currentlyCollecting.equals(resource)) {
                    result.add(vill);
                }
            }
        }
        return result;
    }

    private ArrayList<Villager> getDoingAct(VillagerActivities resource, int amount) {
        ArrayList<Villager> result = new ArrayList<>();
        for (int i = 0; result.size() < amount && i < IGO.units.size(); i++) {
            if (IGO.units.get(i) instanceof Villager) { // to be optimized - extract Villager from other Units
                Villager vill = (Villager) IGO.units.get(i);
                if (vill.task.equals(resource)) {
                    result.add(vill);
                }
            }
        }
        return result;
    }
    /*
    private ArrayList<Villager> mapResourceTypeToArray(VillagerGatherableResource type) {
        ArrayList<Villager> result;
        switch (type) {
            case BERRIES:
                result = IGO.foodVils;
                break;
            case BOAR:
                food += this.CTS.boarcColectSpeed;
                break;
            case DEER:
                food += this.CTS.berriesCollectSpeed;
                break;
            case FARM:
                food += this.CTS.farmCollectSpeed;
                break;
            case GOLD:
                gold += this.CTS.goldCollectSpeed;
                break;
            case GOLDBYTRADE:
                gold += this.CTS.goldByTradeCollectSpeed;
                break;
            case NONE:
                break;
            case SHEEP:
                food += this.CTS.sheepCollectSpeed;
                break;
            case SHOREFISH:
                food += this.CTS.berriesCollectSpeed;
                break;
            case STONE:
                stone += this.CTS.stoneCollectSpeed;
                break;
            case WOOD:
                wood += this.CTS.woodCollectSpeed;
                break;
        }
    }*/
    //what are

}
