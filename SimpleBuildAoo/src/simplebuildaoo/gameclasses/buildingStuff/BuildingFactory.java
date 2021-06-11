/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff;

import resources.Resource;
import resources.Resources;

/**
 *
 * @author absea
 */
public abstract class BuildingFactory {

    abstract public Building createBuilding();
    abstract public String getName();
    abstract public Resource getCost();
}
