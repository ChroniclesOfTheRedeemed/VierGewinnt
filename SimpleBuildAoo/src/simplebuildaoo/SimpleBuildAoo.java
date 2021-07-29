/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo;

import resources.ResourceUnit;
import resources.Sheep;
import OtherStuff.VillagerActivities;
import ingame.StatisticManager;
import java.util.ArrayList;
import resources.Boar;
import simplebuildaoo.gameclasses.Civ;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.UnitFactory;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.House;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.Lumbercamp;
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
        
        TownCenter tc = (TownCenter) player1.IGO.getBuilding(TownCenter.class.getName());
        
        StatisticManager.dos();
        
        //wait
        ArrayList<Sheep> mySheep = new ArrayList<>();
        mySheep.add(new Sheep(player1.IGO.resman));
        mySheep.add(new Sheep(player1.IGO.resman));
        mySheep.add(new Sheep(player1.IGO.resman));
        mySheep.add(new Sheep(player1.IGO.resman));
        
        player1.IGO.waitUp(1);
        player1.build(House.class.getSimpleName(), VillagerActivities.IDLING, 2);
        player1.build(House.class.getSimpleName(), VillagerActivities.IDLING, 1);
        
        tc.tmp.deploy.get(0).createUnit();
        player1.IGO.waitUp(25);
        tc.tmp.deploy.get(0).createUnit();
        
        //player1.IGO.vilman.toResource(mySheep.get(0), VillagerActivities.IDLING, 1);
        
        
        player1.IGO.waitUp(15);
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 4);
                
        player1.IGO.waitUp(25);
        tc.tmp.deploy.get(0).createUnit();
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        
        
        player1.IGO.waitUp(25);
        tc.tmp.deploy.get(0).createUnit();
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        
        
        player1.IGO.waitUp(25);
        tc.tmp.deploy.get(0).createUnit();
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        
        player1.IGO.waitUp(25);
        tc.tmp.deploy.get(0).createUnit();
        player1.IGO.vilman.toResourcevu(mySheep.get(1), VillagerActivities.IDLING, 7);
        player1.build(Lumbercamp.class.getSimpleName(), VillagerActivities.IDLING, 1);
        
        
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        player1.IGO.waitUp(25);
        player1.build(House.class.getSimpleName(), VillagerActivities.IDLING, 1);
        logResources(player1.IGO, mySheep.get(0));
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        player1.IGO.waitUp(25);
        player1.build(House.class.getSimpleName(), VillagerActivities.IDLING, 2);
        logResources(player1.IGO, mySheep.get(0));
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO, mySheep.get(0));
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        ArrayList<UnitFactory> f = new ArrayList<>();

        TownCenter fr = new TownCenter(player1.CTS.townCenterBuilder.tmp);

        player1.IGO.waitUp(25);
        logResources(player1.IGO, mySheep.get(0));
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO, mySheep.get(0));
        player1.IGO.waitUp(700);
        logResources(player1.IGO, mySheep.get(1));
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO, mySheep.get(0));
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);

        System.out.println("fsudihojerzhdfjoir" + (new TownCenter(player1.CTS.townCenterBuilder.tmp).name));
      //  player1.toResourcevu(swwetBoar, VillagerActivities.IDLING, 5);
    }

    private static void logResources(InGameOverview igo, ResourceUnit unit) {
//        System.out.println(igo.currentResources.food);
//        System.out.println(igo.resourcePerSecond.food);
//        System.out.println(igo.currentResources.time);
//        System.out.println("unit has " + unit.currentHoldingResource.food);
//        System.out.println("");
    }

}
