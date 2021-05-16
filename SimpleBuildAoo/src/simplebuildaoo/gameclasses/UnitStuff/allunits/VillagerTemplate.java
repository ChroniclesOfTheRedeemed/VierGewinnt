/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo.gameclasses.UnitStuff.allunits;

import OtherStuff.ResourceType;
import java.util.ArrayList;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.buildingStuff.Building;
import simplebuildaoo.gameclasses.buildingStuff.BuildingFactory;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class VillagerTemplate extends Unit {
    
    public ResourceType currentlyCollecting;
    
    public ArrayList<BuildingFactory> possibleBuildings;
    public void build(String buildingName){
        
    }

    public VillagerTemplate(ResourceType currentlyCollecting, ArrayList<BuildingFactory> possibleBuildings) {
        this.currentlyCollecting = currentlyCollecting;
        this.possibleBuildings = possibleBuildings;
    }
    
    
    
}
