/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import Interfaces.GameWatcher;
import Interfaces.Player;
import java.util.ArrayList;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class GameParticipants {

    public final Player player1;
    public final Player player2;
    public ArrayList<GameWatcher> spectators;

    public GameParticipants(Player player1, Player player2, ArrayList spectators) {
        this.player1 = player1;
        this.player2 = player2;
        this.spectators = spectators;
    }
}
