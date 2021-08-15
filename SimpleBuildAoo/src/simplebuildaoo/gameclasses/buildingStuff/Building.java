/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo.gameclasses.buildingStuff;

import java.util.ArrayList;
import simplebuildaoo.Player;
import simplebuildaoo.gameclasses.Technology;
import simplebuildaoo.gameclasses.UnitStuff.Unit;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class Building {

    public BuildingTemplate tmp = new BuildingTemplate();
    public BuildingStatus status = BuildingStatus.CONSTRUCTING;
    public Player ownedBy;
    public ArrayList<Unit> unitsHere = new ArrayList<>();
    public String name = this.getClass().getSimpleName();
    
    //revertable to abstract class
    public Building(BuildingTemplate tmp){
        this.tmp = tmp;
    }

    public Technology getTechno(int index){//throws status working exception
        return null;
    }
}
