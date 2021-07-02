/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Enums;

import Games.VierGewinnt.SteadyClass;
import Interfaces.BotFactory;
import VierGewinntPlayers.Brutus;
import VierGewinntPlayers.randyplayer;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public enum VierGewinntPlayer1Enum {

    ContainerPlayer(() -> SteadyClass.containerPlayer),
    RandyBot(() -> new randyplayer()),
    RandyBoft(() -> new Brutus());
    
    VierGewinntPlayer1Enum(BotFactory exactplayer){
        thePlayer = exactplayer;
    }
    
    public final BotFactory thePlayer;
}