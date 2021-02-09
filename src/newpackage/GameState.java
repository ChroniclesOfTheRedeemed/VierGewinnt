/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.util.ArrayList;

/**
 *
 * @author absea
 */
public class GameState {
    public ArrayList<ArrayList<Integer>> SpielFeld = new ArrayList<>();
    public boolean player1turn = true;
    public STATUS result = STATUS.GameGoesOn;
    
    public enum STATUS {
        GameGoesOn, Draw, GameWonByMe, GameWonByEnemy
    }
}

