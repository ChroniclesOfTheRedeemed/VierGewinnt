/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import ingame.ResourceManager;

/**
 *
 * @author absea
 */
public class StoneStack extends ResourceUnit{

    public StoneStack(ResourceManager e) {
        super(e);
        this.currentHoldingResource = new Resource(0, 0, 0, 1000, 0, 0);
        this.depletionSpeed = new Resource(0, 0, 0, 0, 0, 1);
        this.meGather = GatherableResource.STONE; // sort of redundant information
    }
}
