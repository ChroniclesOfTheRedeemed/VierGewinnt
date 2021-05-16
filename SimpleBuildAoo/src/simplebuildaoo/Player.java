/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo;

import OtherStuff.Resource;
import java.util.ArrayList;
import simplebuildaoo.gameclasses.Civ;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.TechTreeSheet;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;

/**
 *
 * @author absea
 */
public class Player {
   
    public Civ completeBuildSheet; // (my civ + teamboni as parameter) init at game start, but can be manipulated by technologies
    
    public InGameOverview IGO; // Resources, Units, Building, Age, ?Technologies? 
    
    public ArrayList<Civ> AlliedCivs; // just for fun
    
    public TechTreeSheet CTS;
    
    public Player(ArrayList<Civ> AlliedCivs, Civ completeBuildSheet){
        createMyself(AlliedCivs, completeBuildSheet);
    }
    
    private void createMyself(ArrayList<Civ> AlliedCivs, Civ completeBuildSheet){
        CTS = new TechTreeSheet();
        completeBuildSheet.CivBonus.overwriteTechTree(CTS);
        AlliedCivs.forEach(civ -> {
            civ.TeamBonus.overwriteTechTree(CTS);
        });
        
        IGO = new InGameOverview();
        IGO.currentResources = CTS.startResources;
        IGO.buildings.add(CTS.townCenterBuilder.createBuilding());
        IGO.units.add(CTS.VillagerFactory.createUnit());
        
        calculateResourcesPerSecond(IGO);
        
        //      IGO.buildings.add(new Object());
    }

    public void addResourcesForSecond() {
        IGO.currentResources.time += 0;
        IGO.currentResources.food += IGO.resourcePerSecond.food;
        IGO.currentResources.wood += IGO.resourcePerSecond.wood;
        IGO.currentResources.gold += IGO.resourcePerSecond.gold;
        IGO.currentResources.stone += IGO.resourcePerSecond.stone;

    }
    
    public void calculateResourcesPerSecond(InGameOverview InGameOverview){
        double food = 0, wood = 0, gold = 0, stone = 0;
        for(Villager vil :InGameOverview.collectingVillagers){
            switch(vil.currentlyCollecting){
                case BERRIES:
                    food += this.CTS.berriesCollectSpeed;
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
        }
        InGameOverview.resourcePerSecond = new Resource(food, wood, gold, stone, 0, 1);
    }
    //what are

}
