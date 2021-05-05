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
public enum ChompPlayer1Enum {
    
    ContainerPlayer(SteadyClass.containerPlayer),
    Randy(new VierGewinntPlayers.ChompRandy()),
    Triva(new VierGewinntPlayers.Triva());
    
    ChompPlayer1Enum(Player exactplayer){
        thePlayer = exactplayer;
    }
    public final Player thePlayer;
}