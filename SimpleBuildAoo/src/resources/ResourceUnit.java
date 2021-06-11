/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.ArrayList;
import simplebuildaoo.Event;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;

/**
 *
 * @author absea
 */
public class ResourceUnit {

    public Event depletionEvent;
    public double lastlyUpdated = -1;
    public ArrayList<Villager> gatheres = new ArrayList<>();
    public Resource currentHoldingResource = new Resource();
    public Resource depletionSpeed = new Resource();
    public VillagerGatherableResource meGather;
}
