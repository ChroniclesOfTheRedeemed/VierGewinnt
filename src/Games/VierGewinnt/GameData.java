/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Games.VierGewinnt;

import Interfaces.Game.GameResult;
import java.util.ArrayList;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class GameData {

    public final GameResult result;
    public final int maxMoves;
    public final String Player1Name;
    public final String Player2Name;
    private final ArrayList<Integer> moves;

    GameData(ArrayList<Integer> movesPlayed, GameResult gameResult, String namePlayer1, String namePlayer2) {
        moves = new ArrayList(movesPlayed);
        result = gameResult;
        maxMoves = moves.size();
        Player1Name = namePlayer1;
        Player2Name = namePlayer2;
    }

    public int getMove(int Nr) throws IndexOutOfBoundsException {
        return moves.get(Nr);
    }

    public int getTotalMoves() {
        return moves.size();
    }
}
