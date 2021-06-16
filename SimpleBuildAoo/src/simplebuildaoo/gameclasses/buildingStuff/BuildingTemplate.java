/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff;

import resources.Resource;
import java.util.ArrayList;
import simplebuildaoo.gameclasses.Technology;
import simplebuildaoo.gameclasses.UnitStuff.UnitFactory;

/**
 *
 * @author absea
 */
public class BuildingTemplate {
    
    public Resource cost = new Resource();
    public ArrayList<UnitFactory> deploy = new ArrayList<>();
    public ArrayList<Technology> upgrades = new ArrayList<>();
    public String buildingName = "I forgot to give name";
    
    
    
    

  //  public BuildingTemplate(Resource cost, ArrayList<UnitFactory> deploy, ArrayList<Technology> upgrades) {
  //      this.cost = cost;
  //      this.deploy = deploy;
  //      this.upgrades = upgrades;
  //  }

    
    
   
}
