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

    public void build(String buildingName, VillagerActivities from, int amount) {
        for (BuildingFactory build : CTS.vtmp.possibleBuildings) {
            if (build.tmp.buildingName.equals(buildingName)) {
                IGO.resman.pay(build.tmp.cost); //pop delayed pay (for buildings (as well as units) not units because they reserve themselves their pop w´hen bought
                Event buildEvent = new Event((int) (build.tmp.cost.time + 0.5 + IGO.resman.currentResources.time), (Consumer) (Object t) -> {
                    Building yes = build.createBuilding();
                    this.IGO.buildings.add(yes);
                    this.IGO.vilman.reassignTaskonVillagers(VillagerActivities.BUILDER, VillagerActivities.IDLING, amount);
                    // do the villager coordination thing
                });
                this.IGO.vilman.reassignTaskonVillagers(from, VillagerActivities.BUILDER, amount);
                this.IGO.events.add(buildEvent);
            }
        }
    }

    //guess I have to do it correct once
}
