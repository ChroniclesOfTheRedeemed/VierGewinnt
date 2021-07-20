/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff;

import resources.GatherableResource;
import java.util.ArrayList;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;

/**
 *
 * @author absea
 */
public abstract class ResourceCollectingBuilding extends Building {
    
    ArrayList<Villager> workingVillagers;

    public ResourceCollectingBuilding(BuildingTemplate tmp) {
        super(tmp);
    }
    
    public void addVillager(ArrayList<Villager> vil, GatherableResource res){
        if(collectableHere(res)){

            
            ownedBy.IGO = null;
        }
    }
    
    public ArrayList<Villager> removeVillagerFrom(GatherableResource res, int amount){
        ArrayList<Villager> result = new ArrayList<>();
        if(collectableHere(res)){
            //add to result
            ArrayList<Villager> freeVils = new ArrayList<>();
            ArrayList<Villager> rest = new ArrayList<>();
            for (Villager leaver: workingVillagers) {
                if(leaver.currentlyCollecting.equals(GatherableResource.NONE)){
                    freeVils.add(leaver);
                } else if (leaver.currentlyCollecting.equals(res)) {
                    rest.add(leaver);
                }
            }
            freeVils.addAll(rest);
            result = (ArrayList<Villager>) freeVils.subList(0, amount);
            
            //remove from workers from chest as well as overview
            for (Villager leaver : result) {
                removeVill(leaver);
            }
        }
        return result;
    }
    
    private void addVill(Villager vil) {

    }

    private void removeVill(Villager leaver) {
        workingVillagers.remove(leaver);
        ownedBy.IGO.vilman.collectingVillagers.remove(leaver);

    }


    public abstract boolean collectableHere(GatherableResource res);
}
