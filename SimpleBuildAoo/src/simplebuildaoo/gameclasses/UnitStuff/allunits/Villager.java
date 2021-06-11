/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo.gameclasses.UnitStuff.allunits;

import OtherStuff.VillagerActivities;
import resources.VillagerGatherableResource;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.buildingStuff.Building;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class Villager extends Unit {
    
    public VillagerTemplate tmp;
    public VillagerGatherableResource currentlyCollecting = VillagerGatherableResource.NONE;
    public VillagerActivities task = VillagerActivities.NONE;


    public Villager(VillagerTemplate tmp, VillagerActivities task) {
        this.tmp = tmp;
        this.task = task;
    }
    
    
    
    
    
}
