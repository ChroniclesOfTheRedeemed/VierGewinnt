/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OtherStuff;

/**
 *
 * @author absea
 */
public class Resource {

    public int food;
    public int wood;
    public int gold;
    public int stone;
    public int popLimit;
    public int time;
    
    public Resource(int food, int wood, int gold,  int stone, int popLimit, int time){
        this.food = food;
        this.wood = wood;
        this.gold = gold;
        this.stone = stone;
        this.popLimit = popLimit;
        this.time = time;
    }
}
