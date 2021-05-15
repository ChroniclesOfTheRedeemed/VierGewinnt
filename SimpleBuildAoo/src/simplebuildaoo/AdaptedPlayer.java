/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo;

import java.util.ArrayList;
import simplebuildaoo.gameclasses.Civ;
import simplebuildaoo.gameclasses.InGameOverview;
import simplebuildaoo.gameclasses.TechTreeSheet;

/**
 *
 * @author absea
 */
public class AdaptedPlayer {
   
    public Civ completeBuildSheet; // (my civ + teamboni as parameter) init at game start, but can be manipulated by technologies
    
    public InGameOverview IGO; // Resources, Units, Building, Age, ?Technologies? 
    
    public ArrayList<Civ> AlliedCivs; // just for fun
    
    public TechTreeSheet currentTechSheet;
    
    public AdaptedPlayer(ArrayList<Civ> AlliedCivs, Civ completeBuildSheet){
        createMyself(AlliedCivs, completeBuildSheet);
    }
    
    private void createMyself(ArrayList<Civ> AlliedCivs, Civ completeBuildSheet){
        currentTechSheet = new TechTreeSheet();
        completeBuildSheet.CivBonus.overwriteTechTree(currentTechSheet);
        AlliedCivs.forEach(civ -> {
            civ.TeamBonus.overwriteTechTree(currentTechSheet);
        });
        
        IGO = new InGameOverview();
  //      IGO.buildings.add(new Object());
    }
    //what are

}
