/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo.gameclasses.buildingStuff;

import OtherStuff.Resource;
import java.util.ArrayList;
import simplebuildaoo.AdaptedPlayer;
import simplebuildaoo.Player;
import simplebuildaoo.gameclasses.Technology;
import simplebuildaoo.gameclasses.UnitStuff.Unit;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public abstract class Building {

    public Resource cost;
    public AdaptedPlayer ownedBy;
    public ArrayList<Unit> deploy;
    public ArrayList<Technology> upgrades;

}
