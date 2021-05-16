/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff.allbuilding;

import OtherStuff.ResourceType;
import simplebuildaoo.gameclasses.buildingStuff.ResourceCollectingBuilding;

/**
 *
 * @author absea
 */
public class TownCenter extends ResourceCollectingBuilding {

    
    public TownCenterTemplate tmp;
    @Override
    public boolean collectableHere(ResourceType res) {
        //add exception in future ;) fish and so
        return true;
        
    }

    public TownCenter(TownCenterTemplate tmp) {
        this.tmp = tmp;
    }
    
    
}
