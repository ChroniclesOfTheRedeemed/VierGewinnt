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
public class Boar extends ResourceUnit{

    public Boar(ResourceManager e) {
        super(e);
        this.currentHoldingResource = new Resource(340, 0, 0, 0, 0, 0);
        this.depletionSpeed = new Resource(10, 0, 0, 0, 0, 25);
        this.meGather = GatherableResource.BOAR;
    }
}
