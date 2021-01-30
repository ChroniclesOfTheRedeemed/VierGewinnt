/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayerInterface;

import Interfaces.Game;
import Interfaces.InputListener;
import static java.lang.Math.max;
import java.util.Timer;
import java.util.TimerTask;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class Delayer<Zug> implements GameField<Zug> {

    private final GameField delayField;
    private long delay;
    private final Timer privateTimer;
    private long stampOfLastScheduledAction;

    public Delayer(GameField delayedField) {
        delayField = delayedField;
        delay = 1000;
        stampOfLastScheduledAction = System.currentTimeMillis();
        privateTimer = new Timer();
    }

    public Delayer(GameField delayedField, long standardDelay) {
        delayField = delayedField;
        delay = standardDelay;
        stampOfLastScheduledAction = System.currentTimeMillis();
        privateTimer = new Timer();
    }
    
    public void setStandardDelay(long standardDelay){
        delay = standardDelay;
    }

    @Override
    public void resetField() {
        scheduleTask(new TimerTask() {
            @Override
            public void run() {
                delayField.resetField();
            }
        });
    }

    @Override
    public void updateMoveOnField(Zug move, boolean player1turn) {
        scheduleTask(new TimerTask() {
            @Override
            public void run() {
                delayField.updateMoveOnField(move, player1turn);
            }
        });
    }

    @Override
    public void showResultOnField(Game.GameResult gameResult) {
        scheduleTask(new TimerTask() {
            @Override
            public void run() {
                delayField.showResultOnField(gameResult);
            }
        });
    }

    @Override
    public void setInputListener(InputListener inputGiven) {
        scheduleTask(new TimerTask() {
            @Override
            public void run() {
                delayField.setInputListener(inputGiven);
            }
        }, 5);
    }

    @Override
    public String toString() {
        return delayField.toString();
    }

    private void scheduleTask(TimerTask task) {
        long timeTillNextTask = max(0, stampOfLastScheduledAction - System.currentTimeMillis() + delay);
        privateTimer.schedule(task, timeTillNextTask);
        stampOfLastScheduledAction = System.currentTimeMillis() + timeTillNextTask;
    }

    private void scheduleTask(TimerTask task, long addedDelay) {
        long timeTillNextTask = max(0, stampOfLastScheduledAction - System.currentTimeMillis() + addedDelay);
        privateTimer.schedule(task, timeTillNextTask);
        stampOfLastScheduledAction = System.currentTimeMillis() + timeTillNextTask;
    }
}
