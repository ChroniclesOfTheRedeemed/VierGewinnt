/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PlayerInterface;

import Interfaces.Game;
import Interfaces.InputListener;

/**__DATE__ , __TIME__
 *
 * @author Mike
 * @param <Zug>
 */

public interface GameField<Zug> {

      void resetField();

      void updateMoveOnField(Zug move, boolean player1turn);

      void showResultOnField(Game.GameResult gameResult);

      void setInputListener(InputListener<Zug> inputGiven);

}