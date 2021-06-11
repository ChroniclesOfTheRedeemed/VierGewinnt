/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff.allbuilding;

import simplebuildaoo.gameclasses.buildingStuff.Building;

/**
 *
 * @author absea
 */
public class House extends Building{
    public HouseTemplate htmp;
    
    public House (HouseTemplate tmp){
        htmp = tmp;
    }
}
