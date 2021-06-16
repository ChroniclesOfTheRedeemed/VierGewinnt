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

/**
 *
 * @author absea
 */
public class DarkAge extends Technology {

    public DarkAge(){
        this.cost = new Resource();
    }
    
    @Override
    public void overwriteTechTree(TechTreeSheet CTT) {
        
        CTT.vtmp.cost = new Resource(50, 0, 0, 0, 1, 25);
        CTT.housebuilder.tmp.cost = new Resource(0, 40, 0, 0, -5, 40);
        CTT.townCenterBuilder.tmp.cost = new Resource(0, 275, 0, 0, -5, 180);
        CTT.townCenterBuilder.tmp.deploy.add(CTT.VillagerFactory);
        
        CTT.lumberjackbuilder.tmp.cost = new Resource(0, 100, 0, 0, 0, 40);
        
        CTT.vtmp.possibleBuildings.add(CTT.housebuilder);
        
       // CTT.
        
        CTT.sheepCollectSpeed = 1/3.3;
        CTT.berriesCollectSpeed = 1/3.6;
        CTT.woodCollectSpeed = 1/3.15;
        CTT.farmCollectSpeed = 1/3.6;
        CTT.goldCollectSpeed = 1/3.2;
        CTT.boarcColectSpeed = 1/3.3;
        CTT.deerCollectSpeed = 1/3.3;
        CTT.constructionSpeed = 1;
        
    }

    @Override
    public boolean requirementsMet(InGameOverview IGO, TechTreeSheet currentTechTree) {
        return true;
    }
}
