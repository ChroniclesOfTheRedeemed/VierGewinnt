/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo;

import resources.ResourceUnit;
import resources.Sheep;
import OtherStuff.VillagerActivities;
import java.util.ArrayList;
import resources.Boar;
import simplebuildaoo.gameclasses.Civ;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.UnitFactory;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.House;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.TownCenter;
import simplebuildaoo.gameclasses.civs.Mongols;

/**
 *
 * @author Mike
 */
public class SimpleBuildAoo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*hedgfbhjrt player1 = new hedgfbhjrt();
        initStartResources(player1);
        
        CostumBuilder1 f = new CostumBuilder1(player1);*/

        Civ myCiv = new Mongols();
        ArrayList<Civ> team = new ArrayList<>();
        team.add(myCiv);
        Player player1 = new Player(team, myCiv);
        InGameOverview.dos();
        ArrayList<Unit> vils = player1.IGO.getUnits(Villager.class.getName());
        TownCenter tc = (TownCenter) player1.IGO.getBuilding(TownCenter.class.getName());
        //wait
        Sheep mySheep = new Sheep();
        Boar swwetBoar = new Boar();
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        player1.IGO.waitUp(25);
        player1.build(House.class.getSimpleName(), VillagerActivities.NONE, 1);
        logResources(player1.IGO, mySheep);
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        player1.IGO.waitUp(25);
        player1.build(House.class.getSimpleName(), VillagerActivities.NONE, 2);
        logResources(player1.IGO, mySheep);
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO, mySheep);
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        ArrayList<UnitFactory> f = new ArrayList<>();

        TownCenter fr = new TownCenter(player1.CTS.townCenterBuilder.tmp);

        tc.tmp.deploy.get(0).createUnit();
        tc.tmp.deploy.get(0).createUnit();
        tc.tmp.deploy.get(0).createUnit();
        tc.tmp.deploy.get(0).createUnit();
        player1.IGO.waitUp(25);
        logResources(player1.IGO, mySheep);
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO, mySheep);
        player1.IGO.waitUp(700);
        logResources(player1.IGO, mySheep);
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO, mySheep);
        player1.IGO.waitUp(500);
        player1.toThing(mySheep, VillagerActivities.NONE, 1);

        System.out.println("fsudihojerzhdfjoir" + (new TownCenter(player1.CTS.townCenterBuilder.tmp).name));
        player1.toThing(swwetBoar, VillagerActivities.NONE, 5);
        player1.IGO.waitUp(500);
    }

    private static void logResources(InGameOverview igo, ResourceUnit unit) {
//        System.out.println(igo.currentResources.food);
//        System.out.println(igo.resourcePerSecond.food);
//        System.out.println(igo.currentResources.time);
//        System.out.println("unit has " + unit.currentHoldingResource.food);
//        System.out.println("");
    }

}
