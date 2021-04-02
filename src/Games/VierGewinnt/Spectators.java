/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Games.VierGewinnt;

import Interfaces.GameWatcher;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public enum Spectators {

    ContainerPlayer(SteadyClass.containerPlayer);
    
    
    Spectators(GameWatcher exactplayer){
        theSpeccer = exactplayer;
    }
    
    public final GameWatcher theSpeccer;
}
