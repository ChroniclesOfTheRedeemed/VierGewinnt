/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayerInterface;

import Exceptions.GameStateException;
import Exceptions.MoveNotAvailableException;
import Games.Chomp.ChompPlayer;
import Games.Chomp.ChompWatcher;
import Interfaces.Game;
import Interfaces.InputListener;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class ChompManager implements ChompPlayer, ChompWatcher {

    private final Dimension MOVENOTAVAILABLE = new Dimension(-1, -1);
    private final ChompField myField;
    private Game gameRef;
    private boolean player1turn;
    private boolean gameStillProgressing = false;
    private Dimension currentMove = MOVENOTAVAILABLE;
    private int movesDone;
    ArrayList<InputListener<Dimension>> inputListener = new ArrayList<>();
    
    public ChompManager(ChompField gameField) {
        myField = gameField;
        myField.setInputListener((Dimension move) -> {
            try {
                currentMove = move;
                updateMoveOnField(move);
                if (currentMove == MOVENOTAVAILABLE) {
                    throw new MoveNotAvailableException();
                } else {
                    inputListener.get(player1turn || inputListener.size() == 1 ? 0 : 1 ).inputGiven(move);
                }
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
    public void gameStarted(Game gameRef, InputListener<Dimension> inputListener) {
        this.inputListener.add(inputListener);
        if (!gameStillProgressing) {
            this.gameRef = gameRef;
            gameStillProgressing = true;
            movesDone = 0;
            player1turn = true;
            myField.resetField();
        }}

    @Override
    public void makeMove(Dimension enemyMove) {
        if (!uptodate()) {
            updateMoveOnField(enemyMove);
        }
    }

    @Override
    public void gameEnded(Dimension finishingMove, Game.GameResult gameResult) {
        if (gameStillProgressing) {
            gameStillProgressing = false;
            if (!uptodate()) {
                updateMoveOnField(finishingMove);
            }
            myField.showResultOnField(gameResult);
        }
        inputListener.clear();
    }

    @Override
    public void moveSet(Dimension move) {
        if (!uptodate()) {
            updateMoveOnField(move);
        }
    }

    @Override
    public String toString() {
        return myField.toString();
    }

    private boolean uptodate() {
        return movesDone >= gameRef.movesDone();
    }

    private void updateMoveOnField(Dimension move) {
        myField.updateMoveOnField(move, player1turn);
        movesDone++;
        player1turn = !player1turn;
    }

}
