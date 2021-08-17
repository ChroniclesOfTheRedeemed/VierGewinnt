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
import resources.GoldStack;
import resources.Woodline;
import simplebuildaoo.gameclasses.Civ;
import simplebuildaoo.gameclasses.InGameOverview;
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
        
        TownCenter tc = (TownCenter) player1.IGO.getBuilding(TownCenter.class.getName());
        
        StatisticManager.dos();
        
        //wait
        ArrayList<Sheep> mySheep = new ArrayList<>();
        
        Woodline wood1 = new Woodline(player1.IGO.resman);
        GoldStack myGold = new GoldStack(player1.IGO.resman);
        
        mySheep.add(new Sheep(player1.IGO.resman));
        mySheep.add(new Sheep(player1.IGO.resman));
        mySheep.add(new Sheep(player1.IGO.resman));
        mySheep.add(new Sheep(player1.IGO.resman));
        
        tc.tmp.deploy.get(0).createUnit();
        player1.IGO.waitUntil(1);
        
        player1.build(House.class.getSimpleName(), VillagerActivities.IDLING, 2);
        player1.build(House.class.getSimpleName(), VillagerActivities.IDLING, 1);
        
        player1.IGO.waitUntil(50);
        tc.tmp.deploy.get(0).createUnit();
        player1.IGO.waitUntil(100);
        tc.tmp.deploy.get(0).createUnit();
        
        player1.IGO.vilman.toResourcevu(wood1, VillagerActivities.IDLING, 5);
        
        
        player1.IGO.waitUntil(125);
        tc.tmp.deploy.get(0).createUnit();
        
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        
        
        tc.tmp.deploy.get(0).createUnit();
        tc.tmp.deploy.get(0).createUnit();
        player1.IGO.waitUntil(175);
      //  tc.tmp.deploy.get(0).createUnit();
        player1.IGO.waitUntil(250);
        
        player1.IGO.waitUp(125);
        player1.IGO.waitUp(125);
        player1.IGO.vilman.toResourcevu(mySheep.get(0), VillagerActivities.IDLING, 1);
        
        player1.IGO.vilman.toResourcevu(mySheep.get(1), VillagerActivities.IDLING, 5);
        tc.tmp.deploy.get(0).createUnit();
        tc.tmp.deploy.get(0).createUnit();
        tc.tmp.deploy.get(0).createUnit();
        player1.IGO.waitUp(125);
        player1.IGO.waitUp(125);
        
        player1.IGO.vilman.toResourcevu(wood1, VillagerActivities.IDLING, 5);
        player1.IGO.vilman.toResourcevu(myGold, VillagerActivities.IDLING, 5);
        player1.IGO.vilman.toResourcevu(wood1, VillagerActivities.IDLING, 5);
        player1.IGO.vilman.toResourcevu(mySheep.get(1), VillagerActivities.IDLING, 5);
        //player1.IGO.vilman.toResource(mySheep.get(0), VillagerActivities.IDLING, 1);
        
        player1.IGO.waitUp(125);
        
        player1.IGO.waitUp(125);
        player1.IGO.waitUp(125);
        
        
        System.out.println("fsudihojerzhdfjoir" + (new TownCenter(player1.CTS.townCenterBuilder.tmp).name));
      //  player1.toResourcevu(swwetBoar, VillagerActivities.IDLING, 5);
    }

    private static void logResources(InGameOverview igo, ResourceUnit unit) {
     
        System.out.println("unit has " + unit.currentHoldingResource.food);
        System.out.println("");
    }

}
