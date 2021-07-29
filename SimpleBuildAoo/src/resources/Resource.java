/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

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
    
    public Resource(Resource r) {
        this.food = r.food;
        this.wood = r.wood;
        this.gold = r.gold;
        this.stone = r.stone;
        this.popLimit = r.popLimit;
        this.time = r.time;
    }
 
    public double total() {
        return this.food
                + this.wood
                + this.gold
                + this.stone;
    }
    
    public void add(Resource... a){
        for (Resource ina : a) {
            food += ina.food;
            wood += ina.wood;
            stone += ina.stone;
            gold += ina.gold;
        }
    }
    
    public Resource factor(double factor){
        food *= factor;
        wood *= factor;
        gold *= factor;
        stone *= factor;
        return this;
        
    }
    
    @Override
    public String toString(){
        return "food: " + this.food + "\n"
        + "wood: " + this.wood +"\n"
        +"gold: " + this.gold +"\n"
        +"stone: " + this.stone +"\n";
                
    }
}
