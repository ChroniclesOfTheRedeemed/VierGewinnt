/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ingame;

import java.util.ArrayList;
import resources.Resource;
import simplebuildaoo.gameclasses.TechTreeSheet;
import simplebuildaoo.gameclasses.UnitStuff.allunits.Villager;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class ResourceManager {
    
    public Resource currentResources = new Resource();
    public double totalPop = 0;
    public Resource resourcePerSecond = new Resource();
    
    
    private void addResourcesForSecond() {
        currentResources.time += 0;
        currentResources.food += resourcePerSecond.food;
        currentResources.wood += resourcePerSecond.wood;
        currentResources.gold += resourcePerSecond.gold;
        currentResources.stone += resourcePerSecond.stone;
    }

    
    //shall be changed to use ResourceUnit harvesting soon TM
    public void calculateResourcesPerSecond(ArrayList<Villager> vils, TechTreeSheet CTS) {
        Resource newe = new Resource();
        for (Villager vil : vils) {
            Resource news = CTS.getCollectionSpeedByResouceType(vil.currentlyCollecting);
            newe.food += news.food;
            newe.wood += news.wood;
            newe.gold += news.gold;
            newe.stone += news.stone;
        }

        resourcePerSecond = newe;
    }
    
    
    public void wait(int seconds) {
        currentResources.food += this.resourcePerSecond.food * seconds;
        currentResources.wood += this.resourcePerSecond.wood * seconds;
        currentResources.stone += this.resourcePerSecond.stone * seconds;
        currentResources.gold += this.resourcePerSecond.gold * seconds;
        currentResources.time += seconds;
    }
    
    public void pay(Resource res) {
        currentResources.food -= res.food;
        currentResources.wood -= res.wood;
        currentResources.stone -= res.stone;
        currentResources.gold -= res.gold;
        if (res.popLimit > 0) {
            currentResources.popLimit += res.popLimit;
        } else {
            totalPop -= res.popLimit;
        }
    }
}
