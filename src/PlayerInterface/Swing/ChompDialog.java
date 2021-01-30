/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PlayerInterface.Swing;

import Interfaces.Game;
import Interfaces.InputListener;
import java.awt.Container;
import viergewinntpraxis.EasyDialog;
import PlayerInterface.GameField;
import java.awt.Dimension;

/**__DATE__ , __TIME__
 *
 * @author Mike
 * @param <Zug>
 */
public class ChompDialog<Zug> extends EasyDialog implements PlayerInterface.ChompField {

    GameField gameField;
    
    public  ChompDialog(Container content, GameField field){
        super(content, Game.GameName + " Pane");
        gameField = field;
    }
   
    public <FieldPane extends Container & GameField > ChompDialog(FieldPane fieldPane) {
        super(fieldPane, Game.GameName + " Pane");
        gameField = fieldPane;
    }

    @Override
    public void resetField() {
        this.setVisible(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        gameField.resetField();
    }

    @Override
    public void updateMoveOnField(Dimension move, boolean player1turn) {
        gameField.updateMoveOnField(move, player1turn);
    }

    @Override
    public void showResultOnField(Game.GameResult gameResult) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        gameField.showResultOnField(gameResult);
    }

    @Override
    public void setInputListener(InputListener inputGiven) {
        gameField.setInputListener(inputGiven);
    }
    
    @Override
    public String toString(){
        return gameField.toString();
    }
}
