/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo.gameclasses.buildingStuff;

import java.util.ArrayList;
import simplebuildaoo.Player;
import simplebuildaoo.gameclasses.UnitStuff.Unit;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public abstract class Building {

    public BuildingStatus status = BuildingStatus.CONSTRUCTING;
    public Player ownedBy;
    public ArrayList<Unit> unitsHere = new ArrayList<>();
    
    

}
