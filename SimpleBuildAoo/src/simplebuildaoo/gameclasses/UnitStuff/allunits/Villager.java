/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo.gameclasses.UnitStuff.allunits;

import OtherStuff.VillagerActivities;
import resources.GatherableResource;
import simplebuildaoo.gameclasses.UnitStuff.Unit;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class Villager extends Unit {
    
    public VillagerTemplate tmp;
    public GatherableResource currentlyCollecting = GatherableResource.NONE;
    public VillagerActivities task = VillagerActivities.IDLING;


    public Villager(VillagerTemplate tmp, VillagerActivities task) {
        this.tmp = tmp;
        this.task = task;
    }
    
    
    
    
    
}
