/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Games.VierGewinnt;

import Interfaces.Game;
import java.util.ArrayList;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class Recorder implements Games.VierGewinnt.VierGewinntWatcher {

    private final ArrayList<GameData> recordedGames = new ArrayList<>();
    private ArrayList<Integer> recordingMoves;
    private boolean recording = false; //can only record one game at a time 
    private String nameOfplayer1;
    private String nameOfplayer2;

    public Replay getReplaySession() {
        return new Replay(recordedGames);
    }

    @Override
    public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
        recordedGames.add(new GameData(recordingMoves, gameResult, nameOfplayer1, nameOfplayer2));
        recording = false;
    }

    @Override
    public void gameStarted(Game gameRef) {
        if (!recording) {
            nameOfplayer1 = gameRef.getNameOfPlayer(true);
            nameOfplayer2 = gameRef.getNameOfPlayer(false);
            recordNewGame();
        }
    }

    @Override
    public void moveSet(Integer move) {
        recordingMoves.add(move);
    }

    private void recordNewGame() {
        recording = true;
        recordingMoves = new ArrayList<>();
    }
}
