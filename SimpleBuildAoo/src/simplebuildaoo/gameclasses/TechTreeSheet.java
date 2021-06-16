/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses;

import resources.Resource;
import OtherStuff.VillagerActivities;
import resources.VillagerGatherableResource;
import simplebuildaoo.gameclasses.buildingStuff.Building;
import java.util.ArrayList;
import java.util.function.Consumer;
import simplebuildaoo.Event;
import simplebuildaoo.Player;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.UnitFactory;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;
import simplebuildaoo.gameclasses.UnitStuff.allunits.VillagerTemplate;
import simplebuildaoo.gameclasses.UnitStuff.allunits.military.Scout;
import simplebuildaoo.gameclasses.UnitStuff.allunits.military.ScoutTemplate;
import simplebuildaoo.gameclasses.buildingStuff.BuildingFactory;
import simplebuildaoo.gameclasses.buildingStuff.BuildingTemplate;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.House;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.HouseTemplate;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.Lumbercamp;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.LumbercampTemplate;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.Mill;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.Milltemplate;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.TownCenter;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.TownCenterTemplate;
import simplebuildaoo.gameclasses.technologies.DarkAge;
import simplebuildaoo.gameclasses.technologies.Feudal;

/**
 *
 * @author absea
 */
public class TechTreeSheet {

    public Player ownedBy;

    public ArrayList<BuildingFactory> buildingsThatQualifyForFeudal;
    public ArrayList<BuildingFactory> buildingsThatQualifyForCastle;
    public ArrayList<BuildingFactory> buildingsThatQualifyForImperial;

    public Resource startResources = new Resource(200, 200, 100, 200, 0, 0);

    public VillagerTemplate vtmp = new VillagerTemplate(VillagerGatherableResource.NONE, new ArrayList<>());

    public ScoutTemplate sctmp = new ScoutTemplate();

    public TechTreeSheet(Civ civ) {

    }

    public Technology darkAge = new DarkAge();
    public Technology feudal = new Feudal();

    public UnitFactory VillagerFactory = new UnitFactory() {
        @Override
        public Unit createUnit() {
            Villager result = new Villager(vtmp, VillagerActivities.NONE);
            result.ownedBy = ownedBy;
            result.ownedBy.pay(vtmp.cost);
            Event deployEvent = new Event((int) (vtmp.cost.time + 0.5), (Consumer) (Object t) -> {
                ownedBy.IGO.freeVils.add(result);
                ownedBy.IGO.units.add(result);
            });
            ownedBy.IGO.events.add(deployEvent);
            return result;
        }

        @Override
        public String getName() {
            return Villager.class.getName();
        }
    };

    public UnitFactory scoutFactory = new UnitFactory() {
        @Override
        public Unit createUnit() {
            return new Scout(sctmp);
        }

        @Override
        public String getName() {
            return Scout.class.getName();
        }
    };

    public BuildingFactory<TownCenter> townCenterBuilder = new BuildingFactory<TownCenter>(TownCenter.class.getSimpleName()) {

        
        
        @Override
        public TownCenter createBuilding() {
            TownCenter result = new TownCenter(tmp);
            return result;
        }
    };

    public BuildingFactory housebuilder = new BuildingFactory(House.class.getSimpleName()) {

        @Override
        public House createBuilding() {
            return new House(tmp);
        }
    };

    public BuildingFactory lumberjackbuilder = new BuildingFactory(Lumbercamp.class.getSimpleName()) {
        @Override
        public Lumbercamp createBuilding() {
            return new Lumbercamp(tmp);
        }
    };

    public BuildingFactory millFactory = new BuildingFactory(Mill.class.getSimpleName()) {
        @Override
        public Building createBuilding() {
            return new Mill(tmp);
        }
    };

    public TechTreeSheet(ArrayList<Civ> AlliedCivs, Civ completeBuildSheet) {

    }

    public double constructionSpeed;
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

    public Resource getCollectionSpeedByResouceType(VillagerGatherableResource type) {
        Resource result = new Resource();
        switch (type) {
            case BERRIES:
                result.food = berriesCollectSpeed;
                break;
            case BOAR:
                result.food = boarcColectSpeed;
                break;
            case DEER:
                result.food = deerCollectSpeed;
                break;
            case FARM:
                result.food = farmCollectSpeed;
                break;
            case GOLD:
                result.gold = goldCollectSpeed;
                break;
            case SHEEP:
                result.food = sheepCollectSpeed;
                break;
            case SHOREFISH:
                result.food = shorfishCollectSpeed;
                break;
            case STONE:
                result.stone = stoneCollectSpeed;
                break;
            case WOOD:
                result.wood = woodCollectSpeed;
                break;
            case NONE:
                break;
            default:
                result.food = 1 / 0;
        }
        return result;
    }

    public Technology feudalo;
    public Technology castle;
    public Technology imperial;

}
