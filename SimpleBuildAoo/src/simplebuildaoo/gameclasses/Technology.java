/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses;

import resources.Resource;

/**
 *
 * @author absea
 */
public abstract class Technology {
    
    public abstract void overwriteTechTree(TechTreeSheet currentTechTree);
    
    public abstract boolean requirementsMet(InGameOverview IGO, TechTreeSheet currentTechTree);
    
    public Resource cost = new Resource(); 
}
