/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo.gameclasses.UnitStuff.allunits;

import resources.GatherableResource;
import java.util.ArrayList;
import simplebuildaoo.gameclasses.UnitStuff.UnitTemplate;
import simplebuildaoo.gameclasses.buildingStuff.BuildingFactory;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class VillagerTemplate extends UnitTemplate {
     
    public GatherableResource currentlyCollecting = GatherableResource.NONE;
    
    public ArrayList<BuildingFactory> possibleBuildings = new ArrayList<>();
    public void build(String buildingName){
        
    }

    public VillagerTemplate(GatherableResource currentlyCollecting, ArrayList<BuildingFactory> possibleBuildings) {
        this.currentlyCollecting = currentlyCollecting;
        this.possibleBuildings = possibleBuildings;
    }
    
}
