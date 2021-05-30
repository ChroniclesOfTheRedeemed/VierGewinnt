/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo;

import java.util.ArrayList;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class hedgfbhjrt {
    
    Object teamBoni;
    
    Object completeBuildSheet; // (my civ + teamboni as parameter) init at game start, but can be manipulated by technologies
    
    Object InGameOverview; // Resources, Units, Building, Age, ?Technologies? 
    
    //what are
    
    double wood;
    double food;
    double stone;
    double gold;
    
    private int gameTime;
   
    double woodPerSecond = 0;
    double foodPerSecond = 0;
    double stonePerSecond = 0;
    double goldPerSecond = 0;
    
    
    public int getGameTime(){
        return gameTime;
    }
    
    public void wait(int seconds){
        gameTime+= seconds;
        wood += woodPerSecond*seconds;
        stone += stonePerSecond*seconds;
        food += foodPerSecond*seconds;
        gold += goldPerSecond*seconds;
    }
    
   public void createTeam(ArrayList<hedgfbhjrt> s){
       
   }
    
    
    
    
}
