/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OtherStuff;

import java.util.ArrayList;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;

/**
 *
 * @author absea
 */
public class ResourceUnit {
    public ArrayList<Villager> currentlyCollecting;
    public Resource currentHoldingResource = new Resource();
    public Resource depletionSpeed = new Resource();
    public VillagerGatherableResource meGather;
}
