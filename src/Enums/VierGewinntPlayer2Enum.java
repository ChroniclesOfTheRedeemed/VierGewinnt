/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Enums;

import Games.VierGewinnt.SteadyClass;
import Interfaces.Player;
import newpackage.Brutus;
import newpackage.randyplayer;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public enum VierGewinntPlayer2Enum {
 
    ContainerPlayer(SteadyClass.containerPlayer),
    RandyBot(new randyplayer()),
    RandyBoft(new Brutus());
    
    VierGewinntPlayer2Enum(Player exactplayer){
        thePlayer = exactplayer;
    }
    public final Player thePlayer;
}
