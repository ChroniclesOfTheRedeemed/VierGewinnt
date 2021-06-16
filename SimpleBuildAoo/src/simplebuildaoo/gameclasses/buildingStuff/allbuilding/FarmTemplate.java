/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff.allbuilding;

import simplebuildaoo.gameclasses.buildingStuff.BuildingTemplate;

/**
 *
 * @author absea
 */
public class FarmTemplate extends BuildingTemplate{

    double initialFood; // put special stuff in the factory and hope that the template doesn't need anything special
    
    // template is generic for all buildings
    
    //because there can be no 2 different classes for the same topic (e.g. towncenter, house, etc)
    
    // but also it can hardly be one class
    
    //one workaround (except farms dont even need one) would be to add a caller class into the building object class that gets answered in the factory.
    // this should generally release the template from all to special things
    
    // e.g. farms: Consumer getMaxFarmFood();
    
    // information can not be stored in the object class
    
    
    
    
 //       public FarmTemplate(Resource cost, ArrayList<UnitFactory> deploy, ArrayList<Technology> upgrades) {
 //       super(cost, deploy, upgrades);
 //   }
}
