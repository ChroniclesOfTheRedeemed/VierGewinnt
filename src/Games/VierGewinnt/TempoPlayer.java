/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Games.VierGewinnt;

import PlayerInterface.VierGewinntManager;
import PlayerInterface.Swing.VierGewinntDialog;
import PlayerInterface.Swing.VierGewinntPane;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class TempoPlayer extends VierGewinntManager{
    
    private final int DesiredWidth = 500;
    private final int DesiredDelay = 1000;
    
    public TempoPlayer() {
        super(new VierGewinntDialog(new VierGewinntPane(500, VierGewinntSpiel.SpielFeldHöhe, VierGewinntSpiel.SpielFeldBreite)));
    }
    
}
