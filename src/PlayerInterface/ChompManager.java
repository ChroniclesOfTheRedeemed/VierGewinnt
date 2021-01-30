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
import java.awt.Dimension;
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

    public ChompManager(ChompField gameField) {
        myField = gameField;
        myField.setInputListener((Dimension move) -> {
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
    public void makeMove(Dimension enemyMove) {
        if (!uptodate()) {
            updateMoveOnField(enemyMove);
        }
    }

    @Override
    public Dimension getMove() throws MoveNotAvailableException {
        if (currentMove == MOVENOTAVAILABLE) {
            throw new MoveNotAvailableException();
        } else {
            return currentMove;
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
