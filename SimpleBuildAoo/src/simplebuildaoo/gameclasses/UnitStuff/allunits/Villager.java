/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo.gameclasses.UnitStuff.allunits;

import OtherStuff.ResourceType;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.buildingStuff.Building;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class Villager extends Unit {
    
    public VillagerTemplate tmp;
    public ResourceType currentlyCollecting;
    
    public void build(String buildingName){
        //tmp.possibleBuildings
    }

    public Villager(VillagerTemplate tmp, ResourceType currentlyCollecting) {
        this.tmp = tmp;
        this.currentlyCollecting = currentlyCollecting;
    }
    
    
    
    
    
}
