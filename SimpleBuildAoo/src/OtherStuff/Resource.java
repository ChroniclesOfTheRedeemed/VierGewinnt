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

    public double food,  wood, gold, stone, popLimit, time;
    
    public Resource(double food, double wood, double gold,  double stone, double popLimit, double time){
        this.food = food;
        this.wood = wood;
        this.gold = gold;
        this.stone = stone;
        this.popLimit = popLimit;
        this.time = time;
    }

    public Resource() {
        this.food = 0;
        this.wood = 0;
        this.gold = 0;
        this.stone = 0;
        this.popLimit = 0;
        this.time = 0;
    }
}
