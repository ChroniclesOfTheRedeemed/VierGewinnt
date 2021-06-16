/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff;

/**
 *
 * @author absea
 * @param <BuildingType>
 */
public abstract class BuildingFactory<BuildingType extends Building> {

    public BuildingTemplate tmp = new BuildingTemplate();
    
    abstract public BuildingType createBuilding();

    public BuildingFactory(String buildingName) {
        tmp.buildingName = buildingName;
    }
    
    
}
