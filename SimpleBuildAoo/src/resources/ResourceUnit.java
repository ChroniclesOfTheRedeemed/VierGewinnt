/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import ingame.ResourceManager;
import java.util.ArrayList;
import simplebuildaoo.Event;
import simplebuildaoo.gameclasses.TechTreeSheet;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;

/**
 *
 * @author absea
 */
public class ResourceUnit{

    public double lastlyUpdated = -1;
    //public ArrayList<Villager> gatheres = new ArrayList<>();    not allowed because reasons
    public int gatheres = 0;
    public Resource currentHoldingResource = new Resource();
    public Resource depletionSpeed = new Resource();
    
    public Event depletionEvent;
    public GatherableResource meGather;
    
    public ResourceManager rcmg;

    public ResourceUnit(ResourceManager e){
        rcmg = e;
    }
    
    public Resource updateHoldingOnResource(TechTreeSheet CTS) {// rename to harvest :D
        double currentTime = rcmg.currentResources.time;

        Resource result = new Resource();
        
        if (lastlyUpdated == -1) {
        } else {
            double differenceInTime = currentTime - lastlyUpdated;
            lastlyUpdated = currentTime;
            
            Resource collectionSpeed = CTS.getCollectionSpeedByResouceType(meGather);
            result.time = differenceInTime;
                
            //double raminingfood = calculateRemainder(differenceInTime, currentHoldingResource.food, depletionSpeed.food);
            //double foodharvest = calculateRemainder(differenceInTime, currentHoldingResource.food, collectionSpeed.food * gatheres);
            double lostfood = differenceInTime * depletionSpeed.food;
            double harvestedFood = differenceInTime * collectionSpeed.food * gatheres;
            result.food = harvestedFood;
            currentHoldingResource.food -= harvestedFood - lostfood;

//            double wood = calculateRemainder(collectionSpeed.wood, differenceInTime, currentHoldingResource.wood, depletionSpeed.wood);
//            result.wood = currentHoldingResource.wood - wood;
//            currentHoldingResource.wood = wood;
//            
//            double stone = calculateRemainder(collectionSpeed.stone, differenceInTime, currentHoldingResource.stone, depletionSpeed.stone);
//            result.stone = currentHoldingResource.stone - stone;
//            currentHoldingResource.stone = stone;
//            
//            double gold = calculateRemainder(collectionSpeed.gold, differenceInTime, currentHoldingResource.gold, depletionSpeed.gold);
//            result.gold = currentHoldingResource.gold - gold;
//            currentHoldingResource.gold = gold;
            /* up to deletion after successfull run / debug
            if (collectionSpeed.food != 0) {
                double depletionSpeedv2 = (gatheres.size() * CTS.getCollectionSpeedByResouceType(meGather).food
                        + depletionSpeed.food / depletionSpeed.time);
                double newResource = Math.max(currentHoldingResource.food - differenceInTime * depletionSpeedv2, 0);
                result.food = currentHoldingResource.food - newResource;
                currentHoldingResource.food = newResource;
            }
            double depletionSpeedv2 = (gatheres.size() * CTS.getCollectionSpeedByResouceType(meGather).total()
                    + depletionSpeed.total() / depletionSpeed.time);
            currentHoldingResource.food = Math.max(currentHoldingResource.food - differenceInTime * depletionSpeedv2, 0);
            currentHoldingResource.wood = Math.max(currentHoldingResource.wood - differenceInTime * depletionSpeedv2, 0);
            currentHoldingResource.gold = Math.max(currentHoldingResource.gold - differenceInTime * depletionSpeedv2, 0);
            currentHoldingResource.stone = Math.max(currentHoldingResource.stone - differenceInTime * depletionSpeedv2, 0);
*/
            //System.err.println("so networth is " + (300 - this.IGO.currentResources.food - currentHoldingResource.food));
            //resourceleft = resource there - timepassed * food/time
        }
        return result; //or somthing better
    }

    private double calculateRemainder(double differenceInTime, double currentHoldingResource, double depletionsPeed) {
        double newResource = currentHoldingResource;
        if (depletionsPeed != 0) {
            //double depletionSpeedv2 = (gatheres * collectionSpeed + depletionsPeed);
            newResource = Math.max(currentHoldingResource - differenceInTime * depletionsPeed, 0);
            //result.here = currentHoldingResource - newResource; //should be filled directly to the resource not over new object, #abstract resource from types
        }
        return newResource;
    }
              
    @Override
    public String toString(){
        String result = "";
        result += "lastly updated:" + lastlyUpdated;
        result += "gatherers:" + gatheres ;
        result += "holding:" + currentHoldingResource;
        result += "depletion speed:" + depletionSpeed;
        result += "resource: " + meGather;
        return result;
    }

}