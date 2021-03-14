/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//20.03.19 This junk class reeks of bad code
//special German words, überflüssig, zielführend
package SwingController;

import Games.VierGewinnt.TempoPlayer;
import SwingController.Menu.ControlMenuBar;
import Exceptions.GameStateException;
import Exceptions.MoveNotAvailableException;
import Games.Chomp.ChompGame;
import Interfaces.Game;
import Interfaces.Game.GameResult;
import Interfaces.GameWatcher;
import Interfaces.Player;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import newpackage.randyplayer;
import Games.VierGewinnt.Recorder;
import Games.VierGewinnt.Replay;
import Statistics.Statistic;
import Enums.GameType;
import viergewinntpraxis.GameParticipants;
import Games.VierGewinnt.VierGewinntSpiel;
import Interfaces.InputListener;
import java.awt.event.ActionListener;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class UltimateControl extends JFrame implements GameWatcher<Object> {

    //i dont want this globally...
    private ControlMenuBar cMenu;

    private GameParticipants currentGameParticipants;
    private int remainingGames = 0;
    private ArrayList<Statistic> desiredStatistics = new ArrayList<>();     //? necessary?

    private Game continousGame;

    private GameType selectedGameType;

    public static void main(String args[]) {
        System.out.println(System.getProperty("user.dir"));
        EventQueue.invokeLater(() -> {
            TempoPlayer HI = new TempoPlayer();
            Player[] player1s = {
                HI,
                new randyplayer()
            };
            Player[] player2s = {
                HI,
                new randyplayer()
            };
            ArrayList<GameWatcher> spectators = new ArrayList<>();
            spectators.add(HI);
            spectators.add(new Recorder());

            UltimateControl FGC = new UltimateControl(player1s, player2s, spectators);
            FGC.setVisible(true);
        });
    }

    /**
     * @param possiblePlayer1s
     * @param possiblePlayer2s
     * @param desiredSpectators
     */
    public UltimateControl(Player[] possiblePlayer1s, Player[] possiblePlayer2s, ArrayList<GameWatcher> desiredSpectators) {
        setupFrame(desiredSpectators);
    }

    public UltimateControl(Player[] possiblePlayer1s, Player[] possiblePlayer2s) {
        setupFrame( new ArrayList<>());
    }

    @Override
    public void gameStarted(Game gameRef) {

    }

    @Override
    public void moveSet(Object move) {

    }

    @Override
    public void gameEnded(Object move, GameResult result) {
        remainingGames--;
        if (remainingGames > 0) {
            restartGame();
        } else {
            this.setEnabled(true);
        }
    }

    private void restartGame() {
        try {
            continousGame.playNewGame();
        } catch (GameStateException ex) {
            Logger.getLogger(UltimateControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupFrame( ArrayList<GameWatcher> desiredSpectators) {
        setTitle("4C Control");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        cMenu = new ControlMenuBar(desiredSpectators, (GameType type1) -> {
            setupGameControl(type1);
        });
        this.setJMenuBar(cMenu);

    }

    private void startPlay() {
        try {
            remainingGames = selectedGameType.myControl.getConcussiveGames();
            this.setEnabled(false);
            setupStatistics(currentGameParticipants.player1.toString(), currentGameParticipants.player2.toString());
            setupGameParticipants();
            switch (selectedGameType) {
                case VierGewinnt:
                    continousGame = new VierGewinntSpiel(currentGameParticipants);
                    continousGame.playNewGame();
                    break;
                case VierGewinntReplay:
                    break;
                case Chomp:
                    continousGame = new ChompGame(currentGameParticipants);
                    continousGame.playNewGame();
                    break;
            }
        } catch (GameStateException ex) {
            Logger.getLogger(UltimateControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setupGameParticipants() {
        ArrayList<GameWatcher> newSpectators = cMenu.getSelectedSpectators(); // newly created every single time, so shouldn't be an issue
        newSpectators.addAll(desiredStatistics);
        Player newPlayer1 = selectedGameType.myControl.getSelectedPlayer1();
        Player newPlayer2 = selectedGameType.myControl.getSelectedPlayer2();
        currentGameParticipants = new GameParticipants(newPlayer1, newPlayer2, newSpectators);
        newSpectators.add(this);
    }

    private void setupStatistics(String newPlayer1, String newPlayer2) {
        if (currentGameParticipants.player1.toString().equals(newPlayer1) && currentGameParticipants.player2.toString().equals(newPlayer2)) {
            for (int i = 0; i < desiredStatistics.size(); i++) {
                if (!desiredStatistics.get(i).isVisible()) {
                    desiredStatistics.remove(i);
                    i--;
                }
            }
            desiredStatistics.addAll(cMenu.createStatistics(newPlayer1, newPlayer2));
        } else {
            desiredStatistics = cMenu.createStatistics(newPlayer1, newPlayer2);
        }
    }

    private void setupGameControl(GameType type) {
        System.out.println("gameisbeeing changedd");
        selectedGameType = type;
        currentGameParticipants = new GameParticipants(new Player() {
            @Override
            public void gameStarted(Game gameRef, InputListener inputListener, boolean youHaveFirstMove) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void makeMove(Object enemyMove) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void gameEnded(Object finishingMove, GameResult gameResult) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        },new Player() {
            @Override
            public void gameStarted(Game gameRef, InputListener inputListener, boolean youHaveFirstMove) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void makeMove(Object enemyMove) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void gameEnded(Object finishingMove, GameResult gameResult) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }, new ArrayList());

        ActionListener start = (ActionEvent e) -> {
            startPlay();
        };
        //Auslagern in Game - VierGewinnt United Packages For Reference Usage
        switch (type) {
            case VierGewinnt:
                Player[] mayPLayer1s = new Player[Enums.VierGewinntPlayer1Enum.values().length];
                for (int i = 0; i < mayPLayer1s.length; i++) {
                    mayPLayer1s[i] = Enums.VierGewinntPlayer1Enum.values()[i].thePlayer;
                }
                Player[] mayPLayer2s = new Player[Enums.VierGewinntPlayer2Enum.values().length];
                for (int i = 0; i < mayPLayer2s.length; i++) {
                    mayPLayer2s[i] = Enums.VierGewinntPlayer2Enum.values()[i].thePlayer;
                }
                type.myControl.buildUpContainer(mayPLayer1s, mayPLayer2s, start);
                break;
            case VierGewinntReplay:
                type.myControl.buildUpContainer(new Player[0], new Player[0], start);
                break;
            case Chomp:
                Player[] mayPLayer1ss = new Player[Enums.ChompPlayer1Enum.values().length];
                for (int i = 0; i < mayPLayer1ss.length; i++) {
                    mayPLayer1ss[i] = Enums.ChompPlayer1Enum.values()[i].thePlayer;
                }
                Player[] mayPLayer2ss = new Player[Enums.ChompPlayer2Enum.values().length];
                for (int i = 0; i < mayPLayer2ss.length; i++) {
                    mayPLayer2ss[i] = Enums.ChompPlayer2Enum.values()[i].thePlayer;
                }
                type.myControl.buildUpContainer(mayPLayer1ss, mayPLayer2ss, start);
                break;

        }
        this.setContentPane(type.myControl);
        this.setVisible(true);
    }
}

/**
 * Human spectator how to handle
 *
 * if 1 human is involved, no human spectator is necessary (but not impossible)
 * if no human is involved, human spectator will is togglebar
 *
 * IDEA: ONE basic socket, that handles individual players and will be
 * en/disabled as spectator by default, but can be toggled of.
 *
 * IDEA: If concussive games are enabled (atleast to X amount l like 2-5) ,
 * player as spectator will be toggled off by default If not, human spectator
 * will be enabled (one party watched) Then also the post game will not stay
 * until the next match anymore, won't anyway if concussive games are enabled.
 *
 *
 * 2 Ways to implement Statistical spectators over existing data: 1- make
 * datasets that can be filled into those Statistic thingies 2- "record games"
 * to phantom games that can be played instead of actual games (this solution
 * makes other things more interesting too) - like replaying a game -
 * generalizing data sets - problem is overflow of information evtlually though.
 * - still probably most interesting
 *
 * then we make own gamerecordermodul, that'll be passed by as a spectator and
 * records stuff (genious) then he'll update a stack of phantom games that can
 * be accessed by control. a phantom game will be like a normal game, can be
 * accessed
 *
 * Question: Should recorded games include the time that has passed between the
 * moves? Concrete Asnwer: No, this will not be targetaquiring at the moment
 *
 * IDEA: One gameinstance can be utilized for a whole gaming session (the
 * players can't change). sounds good
 *
 * @author Mike
 */
/**
 * All customized Spectators will be disabled by default when concussive Games
 * surpasses a certain amount. They can be enabled manually in the options
 * though that is slightly deprecated
 *
 * Replays??? Also benefit the humanEye Attribute. 1. GameSession by default. 2.
 * Keep the duality (that isn't even supported atm) /Single Replay can be
 * archived as memorial./*
 *
 */
class ReplayOutcast {

    private Recorder recorder;
    private Replay storedReplay;
}
