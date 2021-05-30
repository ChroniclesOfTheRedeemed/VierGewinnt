/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses;

/**
 *
 * @author absea
 */
public abstract class Civ {
    
    public Technology TeamBonus;
    public Technology CivBonus;
    public Technology darkAgePatch;
    public Technology feudalPatch;
    public Technology castlePatch;
    public Technology imperialPatch;

    public Civ(Technology TeamBonus, Technology CivBonus, Technology darkAge, Technology feudal, Technology castle, Technology imperial) {
        this.TeamBonus = TeamBonus;
        this.CivBonus = CivBonus;
        this.darkAgePatch = darkAge;
        this.feudalPatch = feudal;
        this.castlePatch = castle;
        this.imperialPatch = imperial;
    }
    
    public abstract void pregameAdaptation(InGameOverview IGO);//would not work for other AoE/AoM version, ages add also
    
    
}
