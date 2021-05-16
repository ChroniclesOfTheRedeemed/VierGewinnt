/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff.allbuilding;

import OtherStuff.Resource;
import java.util.ArrayList;
import simplebuildaoo.gameclasses.Technology;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.UnitStuff.UnitFactory;
import simplebuildaoo.gameclasses.buildingStuff.BuildingTemplate;

/**
 *
 * @author absea
 */
public class FarmTemplate extends BuildingTemplate{

    double initialFood;
        public FarmTemplate(Resource cost, ArrayList<UnitFactory> deploy, ArrayList<Technology> upgrades) {
        super(cost, deploy, upgrades);
    }
}
