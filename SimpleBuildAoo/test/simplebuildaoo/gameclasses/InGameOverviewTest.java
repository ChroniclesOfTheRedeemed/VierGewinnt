/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplebuildaoo.gameclasses;

import ingame.StatisticManager;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import simplebuildaoo.Event;
import simplebuildaoo.Player;
import simplebuildaoo.gameclasses.UnitStuff.Unit;
import simplebuildaoo.gameclasses.buildingStuff.Building;
import simplebuildaoo.gameclasses.buildingStuff.allbuilding.TownCenter;
import simplebuildaoo.gameclasses.civs.Mongols;

/**
 *
 * @author Mike
 */
public class InGameOverviewTest {
    
    public InGameOverviewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllUnits method, of class InGameOverview.
     */
    @Test
    public void testGetAllUnits() {
//        System.out.println("getAllUnits");
//        InGameOverview instance = new InGameOverview(CTS);
//        ArrayList<Unit> expResult = null;
//        ArrayList<Unit> result = instance.getAllUnits();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePoplimit method, of class InGameOverview.
     */
    @Test
    public void testUpdatePoplimit() {
        System.out.println("updatePoplimit");
        
        
        Civ myCiv = new Mongols();
        ArrayList<Civ> team = new ArrayList<>();
        team.add(myCiv);
        Player player1 = new Player(team, myCiv);
        
        
        
        
        InGameOverview instance = player1.IGO;

        instance.updatePoplimit();
        int expected = instance.units.size();
        int actual = (int) instance.resman.currentResources.popLimit;
        assertEquals(expected, actual);

        //enough for now, because my testing knowledge is just not so great yet
         
         // add by more possibilites
         
         
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getBuilding method, of class InGameOverview.
     */
    @Test
    public void testGetBuilding() {
//        System.out.println("getBuilding");
//        String name = "";
//        InGameOverview instance = null;
//        Building expResult = null;
//        Building result = instance.getBuilding(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getBuildings method, of class InGameOverview.
     */
    @Test
    public void testGetBuildings() {
//        System.out.println("getBuildings");
//        String name = "";
//        InGameOverview instance = null;
//        ArrayList<Building> expResult = null;
//        ArrayList<Building> result = instance.getBuildings(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getUnit method, of class InGameOverview.
     */
    @Test
    public void testGetUnit() {
//        System.out.println("getUnit");
//        String name = "";
//        InGameOverview instance = null;
//        Unit expResult = null;
//        Unit result = instance.getUnit(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getUnits method, of class InGameOverview.
     */
    @Test
    public void testGetUnits() {
//        System.out.println("getUnits");
//        String name = "";
//        InGameOverview instance = null;
//        ArrayList<Unit> expResult = null;
//        ArrayList<Unit> result = instance.getUnits(name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of updateEvent method, of class InGameOverview.
     */
    @Test
    public void testUpdateEvent() {
//        System.out.println("updateEvent");
//        Event previous = null;
//        Event now = null;
//        InGameOverview instance = null;
//        instance.updateEvent(previous, now);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of waitUp method, of class InGameOverview.
     */
    @Test
    public void testWaitUp() {
//        System.out.println("waitUp");
//        int seconds = 0;
//        InGameOverview instance = null;
//        instance.waitUp(seconds);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
