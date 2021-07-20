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
public class Deer extends ResourceUnit{

    public Deer(ResourceManager e) {
        super(e);
        this.currentHoldingResource = new Resource(140, 0, 0, 0, 0, 0);
        this.depletionSpeed = new Resource(10, 0, 0, 0, 0, 40);
        this.meGather = GatherableResource.DEER;
    }
}
