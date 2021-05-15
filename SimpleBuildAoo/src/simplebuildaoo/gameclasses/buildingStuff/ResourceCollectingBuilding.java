/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff;

import OtherStuff.ResourceType;
import OtherStuff.Resources;
import java.util.ArrayList;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;

/**
 *
 * @author absea
 */
public abstract class ResourceCollectingBuilding extends Building {
    
    ArrayList<Villager> workingVillagers;
    
    public void addVillager(Villager vil, ResourceType res){
        if(collectableHere(res)){
            ownedBy.IGO = null;
        }
    }
    
    public Villager removeVillagerFrom(ResourceType res){
        if(collectableHere(res)){
         //   workingVillagers
        }
        return null;
    }

    public abstract boolean collectableHere(ResourceType res);
}
