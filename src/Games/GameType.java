/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Games;

import Games.Chomp.ChompControl;
import Games.VierGewinnt.VierGewinntControlPane;
import Games.VierGewinnt.VierGewinntReplayControlPane;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public enum GameType {
    VierGewinnt(new VierGewinntControlPane()),//Deceiving your Language is bad habit
    VierGewinntReplay(new VierGewinntReplayControlPane()),
    Chomp(new ChompControl());

    GameType(ControlPane controlPane) {
        myControl = controlPane;
    }

    final public ControlPane myControl;

    public ControlPane getControl() {
        return myControl;
    }
}
