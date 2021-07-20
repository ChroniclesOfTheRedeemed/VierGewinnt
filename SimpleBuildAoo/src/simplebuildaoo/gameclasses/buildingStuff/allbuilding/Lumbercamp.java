/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo.gameclasses.buildingStuff.allbuilding;

import resources.GatherableResource;
import simplebuildaoo.gameclasses.buildingStuff.BuildingTemplate;
import simplebuildaoo.gameclasses.buildingStuff.ResourceCollectingBuilding;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class Lumbercamp extends ResourceCollectingBuilding {

    public Lumbercamp(BuildingTemplate tmp) {
        super(tmp);
    }

    @Override
    public boolean collectableHere(GatherableResource res) {
        return res.equals(GatherableResource.WOOD);

    }

}
