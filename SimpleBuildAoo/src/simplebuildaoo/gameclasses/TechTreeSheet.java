/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses;

import OtherStuff.Resource;
import simplebuildaoo.gameclasses.buildingStuff.Building;
import java.util.ArrayList;
import simplebuildaoo.gameclasses.buildingStuff.MilitaryBuilding;
import simplebuildaoo.gameclasses.buildingStuff.UnitTechCombo;

/**
 *
 * @author absea
 */
public class TechTreeSheet {
    ArrayList<Building> buildingsThatQualifyForFeudal;
    ArrayList<Building> buildingsThatQualifyForCastle;
    ArrayList<Building> buildingsThatQualifyForImperial;
    
    Resource startResources = new Resource(200, 200, 100, 200, 0, 0);
    
    MilitaryBuilding ArcherRange = new MilitaryBuilding() {
        @Override
        public UnitTechCombo getStandardUnit() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public UnitTechCombo getSelfCounteringUnit() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public UnitTechCombo getCounterCounteringUnit() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    Building house = new Building() {};

    public TechTreeSheet() {
        
    }
    
    public void test(){
        
    }
}
