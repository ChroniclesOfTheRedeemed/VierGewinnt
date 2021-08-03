/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo;

import OtherStuff.Sheep;
import OtherStuff.VillagerActivities;
import java.util.ArrayList;
import simplebuildaoo.gameclasses.Civ;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;
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

        //wait
        Sheep mySheep = new Sheep();
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO);
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO);
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO);
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO);
        player1.toThing(mySheep, VillagerActivities.NONE, 1);
        player1.IGO.waitUp(25);
        logResources(player1.IGO);
    }
    
    private static void logResources(InGameOverview igo){
        System.out.println(igo.currentResources.food);
        System.out.println(igo.resourcePerSecond.food);
        System.out.println(igo.currentResources.time);
        System.out.println("");
    }

}
