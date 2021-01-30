/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PlayerInterface;

import Interfaces.Player;
import Exceptions.GameStateException;
import Exceptions.MoveNotAvailableException;
import Games.VierGewinnt.VierGewinntWatcher;
import Interfaces.Game;
import Interfaces.GameWatcher;
import java.util.logging.Level;
import java.util.logging.Logger;
import Games.VierGewinnt.VierGewinntPlayer;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */

public class VierGewinntManager implements VierGewinntPlayer, VierGewinntWatcher {

    private final int MOVENOTAVAILABLE = -1;
    private final VierGewinntFeld myField;
    private Game gameRef;
    private boolean player1turn;
    private boolean gameStillProgressing = false;
    private int currentMove = MOVENOTAVAILABLE;
    private int movesDone;

    public VierGewinntManager(VierGewinntFeld gameField) {
        myField = gameField;
        myField.setInputListener((Integer move) -> {
            try {
                currentMove = move;
                updateMoveOnField(move);
                gameRef.playerMadeMove();
            } catch (GameStateException | MoveNotAvailableException ex) {
                Logger.getLogger(VierGewinntManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void gameStarted(Game gameRef) {
        if (!gameStillProgressing) {
            this.gameRef = gameRef;
            gameStillProgressing = true;
            movesDone = 0;         
            player1turn = true;
            myField.resetField();
        }
    }
    
    @Override
    public void makeMove(Integer enemyMove) {
        if (!uptodate()) {
            updateMoveOnField(enemyMove);
        }
    }

    @Override
    public Integer getMove() throws MoveNotAvailableException {
        if (currentMove == MOVENOTAVAILABLE) {
            throw new MoveNotAvailableException();
        } else {
            return currentMove;
        }
    }

    @Override
    public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
        if (gameStillProgressing) {
            gameStillProgressing = false;
            if (!uptodate()) {
                updateMoveOnField(finishingMove);
            }
            myField.showResultOnField(gameResult);
        }
    }

    @Override
    public void moveSet(Integer move) {
        if (!uptodate()) {
            updateMoveOnField(move);
        }
    }
    
    @Override
    public String toString(){
        return myField.toString();
    }
    
    private boolean uptodate(){
        return movesDone >= gameRef.movesDone();
    }
    
    private void updateMoveOnField(int move){
        myField.updateMoveOnField(move, player1turn);
        movesDone++;
        player1turn = !player1turn;
    }
}