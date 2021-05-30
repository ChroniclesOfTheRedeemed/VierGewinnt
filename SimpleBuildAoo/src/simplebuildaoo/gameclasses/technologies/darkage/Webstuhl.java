/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.technologies.darkage;

import OtherStuff.Resource;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.TechTreeSheet;
import simplebuildaoo.gameclasses.Technology;

/**
 *
 * @author absea
 */
public class Webstuhl extends Technology {

    public Webstuhl() {
        cost = new Resource(0, 0, 50, 0, 0, 25);
    }

    @Override
    public void overwriteTechTree(TechTreeSheet currentTechTree) {

    }

    @Override
    public boolean requirementsMet(InGameOverview IGO, TechTreeSheet currentTechTree) {
        return true;
    }

}
