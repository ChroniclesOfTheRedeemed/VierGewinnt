/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses.buildingStuff;

/**
 *
 * @author absea
 */
public abstract class MilitaryBuilding extends Building {

    public abstract UnitTechCombo getStandardUnit();

    public abstract UnitTechCombo getSelfCounteringUnit();

    public abstract UnitTechCombo getCounterCounteringUnit();
}
