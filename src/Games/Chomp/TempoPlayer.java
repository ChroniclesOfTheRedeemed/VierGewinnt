/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Games.Chomp;

import PlayerInterface.ChompManager;
import PlayerInterface.Swing.ChompDialog;
import PlayerInterface.Swing.ChompSwingPane;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class TempoPlayer extends ChompManager{
    
    private final int DesiredWidth = 500;
    private final int DesiredDelay = 1000;
    
    public TempoPlayer() {
        super(new ChompDialog(new ChompSwingPane(500, Games.Chomp.ChompGame.SpielFeldHöhe, Games.Chomp.ChompGame.SpielFeldBreite)));
    }
    
}
