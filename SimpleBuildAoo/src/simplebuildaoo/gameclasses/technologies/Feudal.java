/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.technologies;

import resources.Resource;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.TechTreeSheet;
import simplebuildaoo.gameclasses.Technology;
import simplebuildaoo.gameclasses.buildingStuff.Building;
import simplebuildaoo.gameclasses.buildingStuff.BuildingFactory;

/**
 *
 * @author absea
 */
public class Feudal extends Technology {

    public Feudal() {
        cost = new Resource(500, 0, 0, 0, 0, 120);
    }

    
    
    @Override
    public void overwriteTechTree(TechTreeSheet currentTechTree) {
       //currentTechTree.tctmp.deploy.add(e)
    }

    @Override
    public boolean requirementsMet(InGameOverview IGO, TechTreeSheet currentTechTree) {
        
        //change to existing building requirement
        // new requirememt will be researched technology
        boolean result;
        if (currentTechTree.buildingsThatQualifyForFeudal.size() > 0) {
            boolean quickResult = false;
            for (BuildingFactory neededBuilding : currentTechTree.buildingsThatQualifyForFeudal) {

                quickResult = false;
                for (Building existingBuilding : IGO.buildings) {
                    if (existingBuilding.getClass().getName().equals(neededBuilding.getName())) {
                        quickResult = true;
                        break;
                    }
                }
                if (!quickResult) {
                    
                    break;
                }
            }
            result = quickResult;
        } else {
            result = true;
        }
        return result;
        //currentTechTree.buildingsThatQualifyForFeudal
    }

    
}
