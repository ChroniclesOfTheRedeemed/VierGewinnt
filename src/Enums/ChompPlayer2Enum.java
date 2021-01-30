/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Enums;

import Games.Chomp.SteadyClass;
import Interfaces.Player;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public enum ChompPlayer2Enum {
    
    ContainerPlayer(SteadyClass.containerPlayer),
    Randy(new newpackage.ChompRandy()),
    Triva(new newpackage.Triva());
    
    ChompPlayer2Enum(Player exactplayer){
        thePlayer = exactplayer;
    }
    public final Player thePlayer;
}