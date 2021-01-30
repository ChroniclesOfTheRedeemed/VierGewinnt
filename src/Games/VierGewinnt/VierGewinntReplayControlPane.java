/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Games.VierGewinnt;

import Exceptions.MoveNotAvailableException;
import Games.ControlPane;
import Interfaces.Game;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class VierGewinntReplayControlPane extends ControlPane {

   
    
    
    @Override
    public void setupBasicPane() {
        setLayout(new BorderLayout());
        add(getTopContainer(), BorderLayout.NORTH);
        add(getLeftContainer(), BorderLayout.WEST);
        add(getMiddleContainer(), BorderLayout.CENTER);
        add(getRightContainer(), BorderLayout.EAST);
        add(getBottomContainer(), BorderLayout.SOUTH);
        selectedPlayer1 = new VierGewinntPlayer() {
            @Override
            public void gameStarted(Game gameRef) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void makeMove(Integer enemyMove) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Integer getMove() throws MoveNotAvailableException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public String toString(){
                return "humpf";
            }
        };
        
        selectedPlayer2 = new VierGewinntPlayer() {
            @Override
            public void gameStarted(Game gameRef) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void makeMove(Integer enemyMove) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Integer getMove() throws MoveNotAvailableException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void gameEnded(Integer finishingMove, Game.GameResult gameResult) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            @Override
            public String toString(){
                return "hampf";
            }
        };
    }
    

    private JButton getStartGameButton() {
        JButton startGameButton = new JButton("Start");
        startGameButton.addActionListener(startu);
        return startGameButton;
    }



    private JSpinner getConcussiveGamesTextField() {
        JSpinner concGames = new JSpinner();
        concussive = 0;
        concGames.setValue(concussive);
        concGames.addChangeListener((ChangeEvent e) -> {
            int newVal = (int) concGames.getValue();
            if ((int) (concussive / 10) != (int) (newVal / 10)) {

                concGames.updateUI();
            }
            concussive = newVal;
        });
        return concGames;
    }

    private JLabel getVSLabel() {
        JLabel vsLabel = new JLabel("vs", SwingConstants.CENTER);
        vsLabel.setPreferredSize(new Dimension(50, 40));
        return vsLabel;
    }

    private Container getTopContainer() {
        return new Container();
    }

    private Container getLeftContainer() {
        Container playerPane = new Container();
        playerPane.setLayout(new FlowLayout());
        playerPane.add(new JLabel("Player x"));
        return playerPane;
    }

    private Container getRightContainer() {
        Container playerPane = new Container();
        playerPane.setLayout(new FlowLayout());
        playerPane.add(new JLabel("Player y"));
        return playerPane;
    }

    private Container getMiddleContainer() {
        Container middlePane = new Container();
        middlePane.setLayout(new BorderLayout());
        middlePane.add(getVSLabel(), BorderLayout.NORTH);
        return middlePane;
    }

    private Container getBottomContainer() {
        Container bottomContainer = new Container();
        bottomContainer.setLayout(new FlowLayout());
        bottomContainer.add(new JLabel("concussive Games"));
        bottomContainer.add(getConcussiveGamesTextField());
        bottomContainer.add(getStartGameButton());
        bottomContainer.setPreferredSize(new Dimension(100, 60));
        return bottomContainer;
    }
}