/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.technologies;

import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.TechTreeSheet;
import simplebuildaoo.gameclasses.Technology;

/**
 *
 * @author absea
 */
public class Feudal extends Technology {

    @Override
    public void overwriteTechTree(TechTreeSheet currentTechTree) {
       //currentTechTree.tctmp.deploy.add(e)
    }    

    @Override
    public void requirementsMet(InGameOverview IGO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
