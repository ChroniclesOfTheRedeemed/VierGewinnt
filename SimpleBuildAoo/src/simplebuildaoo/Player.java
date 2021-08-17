/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo;

import OtherStuff.VillagerActivities;
import java.util.ArrayList;
import java.util.function.Consumer;
import simplebuildaoo.gameclasses.Civ;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.TechTreeSheet;
import simplebuildaoo.gameclasses.buildingStuff.Building;
import simplebuildaoo.gameclasses.buildingStuff.BuildingFactory;
import simplebuildaoo.gameclasses.buildingStuff.BuildingStatus;

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

        IGO = new InGameOverview(CTS);
        IGO.resman.currentResources = CTS.startResources;
        IGO.buildings.add(CTS.townCenterBuilder.createBuilding());
        IGO.units.add(CTS.VillagerFactory.createUnit());
        IGO.units.add(CTS.VillagerFactory.createUnit());
        IGO.units.add(CTS.VillagerFactory.createUnit());
        IGO.units.add(CTS.scoutFactory.createUnit());

        CTS.darkAge.overwriteTechTree(CTS);
        completeBuildSheet.darkAgePatch.overwriteTechTree(CTS);
        IGO.resman.calculateResourcesPerSecond(IGO.vilman.allVills, CTS);

    }

    private double buildTimeFormula(double timeForOne, int amount) {
        return 3 * timeForOne / (2 + amount);
    }

    //should be sort of decent now
    public void build(String buildingName, VillagerActivities from, int amount) {
        boolean buildingExists = false;
        for (BuildingFactory build : CTS.vtmp.possibleBuildings) {
            if (build.tmp.buildingName.equals(buildingName)) {
                buildingExists = true;
                double buildTime = buildTimeFormula(build.tmp.cost.time, amount);

                IGO.resman.pay(build.tmp.cost); //pop delayed pay (for buildings (as well as units) not units because they reserve themselves their pop w´hen bought

                Building yes = build.createBuilding();
                yes.status = BuildingStatus.CONSTRUCTING;
                this.IGO.buildings.add(yes); // not found before finish building, hm
                
                //causes problem in countings who dont respect status state!!
                // if access buldings always use is stuts constructing!!

                this.IGO.vilman.reassignTaskonVillagers(from, VillagerActivities.BUILDER, amount);
                
                Event buildEvent = new Event((int) (buildTime + IGO.resman.currentResources.time), (Consumer) (Object t) -> {

                    yes.status = BuildingStatus.IDLE;
                    IGO.resman.totalPop -= build.tmp.cost.popLimit; // buildings only give after build successfull

                    // do the villager coordination thing
                    // yeah ressign them man, they got stuff to do
                    this.IGO.vilman.reassignTaskonVillagers(VillagerActivities.BUILDER, VillagerActivities.IDLING, amount);
                    
                });
                this.IGO.events.add(buildEvent);
            } 
        }
        if (!buildingExists){
            System.out.println("Building could not be found: "  +  buildingName);
        }
    }

    //guess I have to do it correct once
}
