/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SwingController.Menu;

import Enums.GameType;

/**
 *
 * @author Mike
 */
public interface GameChangedListener {
    
    public void gameChanged(GameType type);
}
