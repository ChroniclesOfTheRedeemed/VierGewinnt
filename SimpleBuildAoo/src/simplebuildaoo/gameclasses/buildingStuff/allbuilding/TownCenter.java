/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff.allbuilding;

import resources.GatherableResource;
import simplebuildaoo.gameclasses.buildingStuff.BuildingTemplate;
import simplebuildaoo.gameclasses.buildingStuff.ResourceCollectingBuilding;

/**
 *
 * @author absea
 */
public class TownCenter extends ResourceCollectingBuilding {

    public TownCenter(BuildingTemplate tmp) {
        super(tmp);
    }

    @Override
    public boolean collectableHere(GatherableResource res) {
        //add exception in future ;) fish and so
        return true;
        
    }

    
}
