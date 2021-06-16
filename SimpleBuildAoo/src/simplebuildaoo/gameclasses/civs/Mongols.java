/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.civs;

import simplebuildaoo.gameclasses.Civ;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.TechTreeSheet;
import simplebuildaoo.gameclasses.Technology;

/**
 *
 * @author absea
 */
public class Mongols extends Civ {

    public Mongols() {
        super(new Technology() { //TeamBoni
            @Override
            public void overwriteTechTree(TechTreeSheet currentTechTree) {
            }

            @Override
            public boolean requirementsMet(InGameOverview IGO, TechTreeSheet currentTechTree) {
                return true;
            }

        }, new Technology() {//Civ
            @Override
            public void overwriteTechTree(TechTreeSheet currentTechTree) {
                currentTechTree.boarcColectSpeed *= 1.5;
                currentTechTree.deerCollectSpeed *= 1.5;
            }

            @Override
            public boolean requirementsMet(InGameOverview IGO, TechTreeSheet currentTechTree) {
                return true;
            }
        }, new Technology() {//dark

            @Override
            public boolean requirementsMet(InGameOverview IGO, TechTreeSheet currentTechTree) {
                return true;
            }

            @Override
            public void overwriteTechTree(TechTreeSheet currentTechTree) {
            }
        }, new Technology() {//feudal
            @Override
            public void overwriteTechTree(TechTreeSheet currentTechTree) {
                currentTechTree.boarcColectSpeed *= 1.5;
                currentTechTree.deerCollectSpeed *= 1.5;
            }

            @Override
            public boolean requirementsMet(InGameOverview IGO, TechTreeSheet currentTechTree) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }, new Technology() {//castle
            @Override
            public void overwriteTechTree(TechTreeSheet currentTechTree) {
                currentTechTree.boarcColectSpeed *= 1.5;
                currentTechTree.deerCollectSpeed *= 1.5;
            }

            @Override
            public boolean requirementsMet(InGameOverview IGO, TechTreeSheet currentTechTree) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }, new Technology() {//imp
            @Override
            public void overwriteTechTree(TechTreeSheet currentTechTree) {
                currentTechTree.boarcColectSpeed *= 1.5;
                currentTechTree.deerCollectSpeed *= 1.5;
            }

            @Override
            public boolean requirementsMet(InGameOverview IGO, TechTreeSheet currentTechTree) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    @Override
    public void pregameAdaptation(InGameOverview IGO) {
    }

}
