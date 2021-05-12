/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo;

/**
 *
 * @author Mike
 */
public class SimpleBuildAoo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Player player1 = new Player();
        initStartResources(player1);
        
        CostumBuilder1 f = new CostumBuilder1(player1);
        
    }
    
    private static void initStartResources(Player player1){
        player1.food = 200;
        player1.wood = 200;
        player1.stone = 200;
        player1.gold = 100;
    }
    
  
    
}
