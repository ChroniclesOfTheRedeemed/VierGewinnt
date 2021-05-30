/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simplebuildaoo;

import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.JDialog;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class CostumBuilder1 {

    private hedgfbhjrt player1;
    private DefaultCategoryDataset woodLine = new DefaultCategoryDataset();
    private DefaultCategoryDataset foodLine = new DefaultCategoryDataset();
    private DefaultCategoryDataset stoneLine = new DefaultCategoryDataset();
    private DefaultCategoryDataset goldLine = new DefaultCategoryDataset();
    
    ArrayList freeVils = new ArrayList();
    ArrayList buildingVils = new ArrayList();
    ArrayList walkingVills = new ArrayList();
    ArrayList woodVils = new ArrayList();
    ArrayList foodVils = new ArrayList();
    ArrayList stoneVils = new ArrayList();
    ArrayList goldVils = new ArrayList();
    
    ArrayList<Event> events = new ArrayList<>();
    
    class Event {
        final int gameTime;
        Consumer<Object> method;

        public Event(int gameTime, Consumer<Object> method) {
            this.gameTime = gameTime;
            this.method = method;
        }
    }
    
    
    public CostumBuilder1(hedgfbhjrt player) {
        player1 = player;
        freeVils.add(0);
        freeVils.add(0);
        freeVils.add(0);
        createBuild();
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Builder",
                "Count", "Wood",
                woodLine,
                PlotOrientation.VERTICAL,
                true, true, false);

      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
       JDialog x1 = new JDialog();
              x1.setContentPane(chartPanel);
              x1.pack();
              x1.setVisible(true);
              
      
      JFreeChart lineChart2 = ChartFactory.createLineChart(
         "Builder",
         "Count", "Food",
         foodLine,
         PlotOrientation.VERTICAL,
         true,true,false);
         

      ChartPanel chartPanel2 = new ChartPanel( lineChart2 );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
       JDialog x2 = new JDialog();
              x2.setContentPane(chartPanel2);
              x2.pack();
              x2.setVisible(true);
              
      
      JFreeChart lineChart3 = ChartFactory.createLineChart(
         "Builder",
         "Count", "Stone",
         stoneLine,
         PlotOrientation.VERTICAL,
         true,true,false);
         

      ChartPanel chartPanel3 = new ChartPanel( lineChart3 );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
       JDialog x3 = new JDialog();
              x3.setContentPane(chartPanel3);
              x3.pack();
              x3.setVisible(true);
              
      
      JFreeChart lineChart4 = ChartFactory.createLineChart(
         "Builder",
         "Count", "Gold",
         goldLine,
         PlotOrientation.VERTICAL,
         true,true,false);
         

      ChartPanel chartPanel4 = new ChartPanel( lineChart4 );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      JDialog x4 = new JDialog();
              x4.setContentPane(chartPanel4);
              x4.pack();
              x4.setVisible(true);
              
    }
    
    private void createBuild(){
        tcVill(11);//3
        buildHouse(freeVils);
        buildHouse(freeVils);
        waitUp(30);//4
        freeVils.remove(0);
        freeVils.remove(0);
        freeVils.remove(0);
        freeVils.remove(0);
        foodVils.add(0);
        foodVils.add(0);
        foodVils.add(0);
        foodVils.add(0);
        player1.foodPerSecond += 1/3.4*5;
        waitUp(20);//5
        foodVils.add(0);
        waitUp(25);//6
        foodVils.add(0);
        player1.foodPerSecond += 1/3.4;
        //estimate sheep done at 7*60 seconds
        for (int i = 0; i < 6; i++) {
            waitUp(25);
            toWood(freeVils);
            System.out.println(player1.woodPerSecond);
        }//12
        
        buildStuff(woodVils, 100, 1, 25);//Lumbercamp
        waitUp(25);//13
        buildHouse(freeVils);
        waitUp(25);//14
        freeVils.remove(0);
        freeVils.remove(0);
        walkingVills.add(0);
        walkingVills.add(0);
        player1.gold-=50;
        waitUp(25);
        tcVill(20);
        
        waitUp(25);//15
        
        buildStuff(freeVils, 100, 1, 25);//mill
        waitUp(25);//16
        toBerries(freeVils);
        toBerries(freeVils);
        waitUp(25);//17
        walkingVills.add(0);//eberpull (takes about 50 sec)
        freeVils.remove(0);
        waitUp(25);//18
        toBerries(freeVils);
        buildHouse(walkingVills);
        waitUp(25);//19
        toBerries(freeVils);
        player1.foodPerSecond -= 1 / 3.4 * 6;//sheep done
        foodVils.add(0);
        walkingVills.remove(0);
        doBoar(foodVils, 7);
        waitUp(25);//20
        buildFarm(freeVils, 0);
        waitUp(25);//21
        buildFarm(freeVils, 0);
        waitUp(25);//22
        walkingVills.add(0);//pull 2 Boar
        freeVils.remove(0);
        waitUp(25);//23
        buildFarm(freeVils, 0);
        buildHouse(walkingVills);
        waitUp(25);//24
        toWood(freeVils);
        buildStuff(woodVils, 100, 1, 25);//2nd Lumbercamp
        foodVils.add(0);
        walkingVills.remove(0);
        doBoar(foodVils, 8);
        
        waitUp(25);//25
        toWood(freeVils);
        waitUp(25);//26
        toWood(freeVils);
        waitUp(25);//27
        toWood(freeVils);
        buildHouse(walkingVills);
        waitUp(25);//28
        toWood(freeVils);
        toWood(foodVils);
        for (int i = 0; i < 4; i++) {
            buildFarm(foodVils, 0);
        }
        for (int i = 0; i < 3; i++) {
            toGold(foodVils);
        }
        waitUp(25);//29
        buildFarm(freeVils, 0);
        waitUp(25);//30
        buildFarm(freeVils, 0);
        waitUp(25);//31
        buildFarm(freeVils, 0);
        waitUp(25);//32
        buildStuff(freeVils, 100, 1, 25);
        waitUp(25);//33
        toGold(freeVils);
        toGold(freeVils);
        waitUp(25);//34
        toGold(freeVils);
        player1.food -= 500;//go next age.
        buildStuff(walkingVills, 175, 1, 50);
        buildHouse(walkingVills);
        //berries done
        
        player1.foodPerSecond-= 1/3.6*4;
        buildFarm(foodVils, 0);
        buildFarm(foodVils, 0);
        buildFarm(foodVils, 0);
        buildFarm(foodVils, 0);
        waitUp(130);
        tcVill(2);
        buildStuff(walkingVills, 175, 1, 50);
        buildStuff(walkingVills, 150, 1, 45);
        buildHouse(woodVils);
        waitUp(25);
        buildFarm(freeVils, 0);
        waitUp(25);
        toGold(freeVils);
        player1.food-= 800;//go next age.
        player1.gold-= 200;//go next age.
        fromWood(3);
        buildFarm(woodVils, 0);
        buildFarm(woodVils, 0);
        buildFarm(woodVils, 0);
        buildHouse(walkingVills);
        waitUp(160);
        tcVill(2);
        buildStuff(woodVils, 200, 2, 45);//uni
        buildStuff(walkingVills, 200, 1, 45);//werft
        
        waitUp(25);
        toGold(freeVils);
        waitUp(25);
        toGold(freeVils);
        player1.food-=1000;
        player1.gold-=800;
        waitUp(90);
        player1.food-=100; //f upgrade
        waitUp(50);
        player1.food-=150; //f upgrade
        player1.gold-=150; //f upgrade
        buildStuff(walkingVills, 175, 1, 45);
        waitUp(50);
        player1.food-=250; //f upgrade
        player1.gold-=250; //f upgrade
        waitUp(10);
        
    }

    private void toGold(ArrayList who) {
        player1.goldPerSecond += 1 / 3.0;
        who.remove(0);
        goldVils.add(0);
    }
    private void doBoar(ArrayList who, int howMany) {
        for (int i = 0; i < howMany; i++) {
            who.remove(0);
            foodVils.add(0);
        }
        double speed = 1 / 3.3 * howMany;
        player1.foodPerSecond += speed;
        Event done = new Event((int) (player1.getGameTime() + 300 / speed), (Object t) -> {
//            for (int i = 0; i < howMany; i++) {
//                freeVils.add(0);
//                foodVils.remove(0);
//            }
            player1.foodPerSecond -= speed;
        });

        events.add(done);
    }
    
    private void toBerries(ArrayList who){
        who.remove(0);
        foodVils.add(0);
        player1.foodPerSecond+=1/3.6;
    }
    private void buildStuff(ArrayList who, int wood, int howMany, int timeNeeded) {
        player1.wood -= wood;
        for (int i = 0; i < howMany; i++) {
            who.remove(0);
            buildingVils.add(0);
        }
        Event done = new Event(player1.getGameTime() + timeNeeded, (Object t) -> {
            for (int i = 0; i < howMany; i++) {
                who.add(0);
                buildingVils.remove(0);
            }
        });
        
        events.add(done);
    }
    private void toWood(ArrayList who) {
        player1.woodPerSecond += 1.0 / 3.15;
        who.remove(0);
        woodVils.add(0);
    }
    
    private void fromWood(int howMany) {
        player1.woodPerSecond -= 1.0 / 3.15 / howMany;

    }

    private void tcVill(int num) {
        player1.food -= 50;
        Event done = new Event(player1.getGameTime() + 25, (Object t) -> {
            freeVils.add(0);
            if (num > 0) {
                tcVill(num - 1);
            }
        });
        events.add(done);
    }
    
    private void buildHouse(ArrayList who){
        player1.wood-= 40;
        who.remove(0);
        buildingVils.add(0);
        Event done = new Event(player1.getGameTime() + 25, (Object t) -> {
            who.add(0);
            buildingVils.remove(0);
        });
        events.add(done);
    }
    
    private void buildFarm(ArrayList who, int upgrade){
        int farmFood = 175;
        switch(upgrade){
            case 1:
                farmFood = 250;
                break;
            case 2:
                farmFood = 375;
                break;
        }
        
        player1.wood-= 60;
        who.remove(0);
        foodVils.add(0);
        
        double farmingSpeed = 1/3.6;
        player1.foodPerSecond += farmingSpeed;
        int timeUntilRunOut = (int) (farmFood/farmingSpeed);
        Event done = new Event(player1.getGameTime() + timeUntilRunOut, (Object t) -> {
            player1.foodPerSecond-= farmingSpeed;
            freeVils.add(0);
            foodVils.remove(0);
            buildFarm(who, upgrade);
        });
        
        events.add(done);
    }
    
    private void waitUp(int seconds) {
        for (int i = 0; i < seconds; i++) {
            for (int j = 0; j < events.size(); j++) {
                if(events.get(j).gameTime <= player1.getGameTime()){
                    events.get(j).method.accept(null);
                    events.remove(j);
                    j--;
                }
            }
            
            
            
            
            
            
            
            
            
            
            
            woodLine.addValue(player1.wood, "Wood", Integer.valueOf(player1.getGameTime()));
            foodLine.addValue(player1.food, "Food", Integer.valueOf(player1.getGameTime()));
            stoneLine.addValue(player1.stone, "Stone", Integer.valueOf(player1.getGameTime()));
            goldLine.addValue(player1.gold, "Gold", Integer.valueOf(player1.getGameTime()));
            player1.wait(1);
        }
    }
    
}





