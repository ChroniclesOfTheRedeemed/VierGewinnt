/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses;

import OtherStuff.Resource;
import OtherStuff.ResourceType;
import simplebuildaoo.gameclasses.buildingStuff.Building;
import java.util.ArrayList;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.UnitFactory;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;
import simplebuildaoo.gameclasses.UnitStuff.allunits.VillagerTemplate;
import simplebuildaoo.gameclasses.buildingStuff.BuildingFactory;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.TownCenter;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.TownCenterTemplate;

/**
 *
 * @author absea
 */
public class TechTreeSheet {
    public ArrayList<BuildingFactory> buildingsThatQualifyForFeudal;
    public ArrayList<BuildingFactory> buildingsThatQualifyForCastle;
    public ArrayList<BuildingFactory> buildingsThatQualifyForImperial;
    
    public Resource startResources = new Resource(200, 200, 100, 200, 0, 0);
    
    public VillagerTemplate vtmp = new VillagerTemplate(ResourceType.NONE, new ArrayList<>());
    
    public TownCenterTemplate tctmp = new TownCenterTemplate(startResources, new ArrayList<>(), new ArrayList<>());

    
    public UnitFactory VillagerFactory = new UnitFactory() {
        @Override
        public Unit createUnit() {
            return new Villager(vtmp, ResourceType.NONE);
        }

        @Override
        public String getName() {
            return Villager.class.getName();
        }
    };
    
    public BuildingFactory townCenterBuilder = new BuildingFactory() {
        @Override
        public Building createBuilding() {
            TownCenter result = new TownCenter(tctmp);
          //  TownCenter = new TownCenterTemplate();
          
            return result;
        }

        @Override
        public String getName() {
            return TownCenter.class.getName();
        }
    };
    
    public Building house = new Building() {};

    public TechTreeSheet() {
        
    }
    
    public void test(){
        
    }
    
    public double boarcColectSpeed;
    public double sheepCollectSpeed;
    public double deerCollectSpeed;
    public double berriesCollectSpeed;
    public double farmCollectSpeed;
    public double shorfishCollectSpeed;
    public double woodCollectSpeed;
    public double goldCollectSpeed;
    public double goldByTradeCollectSpeed;
    public double stoneCollectSpeed;
    
    
    public double getCollectionSpeedByResouceType(ResourceType type){
        double result;
        switch (type) {
            case BERRIES:
                result = berriesCollectSpeed;
                break;
            case BOAR:
                result = boarcColectSpeed;
                break;
            case DEER:
                result = deerCollectSpeed;
                break;
            case FARM:
                result = farmCollectSpeed;
                break;
            case GOLD:
                result = goldCollectSpeed;
                break;
            case GOLDBYTRADE:
                result = goldByTradeCollectSpeed;
                break;
            case SHEEP:
                result = berriesCollectSpeed;
                break;
            case SHOREFISH:
                result = shorfishCollectSpeed;
                break;
            case STONE:
                result = stoneCollectSpeed;
                break;
            case WOOD:
                result = woodCollectSpeed;
                break;
            case NONE:
                result = 0;
                break;
            default:
                result = 1/0;
        }
        return result;
    }

    public Technology feudal;
    public Technology castle;
    public Technology imperial;

}
