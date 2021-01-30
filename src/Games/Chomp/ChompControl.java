/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Games.Chomp;

import Games.ControlPane;
import Interfaces.Player;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class ChompControl extends ControlPane {

    
    @Override
    public void setupBasicPane() {
        setLayout(new BorderLayout());
        add(getTopContainer(), BorderLayout.NORTH);
        add(getLeftContainer(), BorderLayout.WEST);
        add(getMiddleContainer(), BorderLayout.CENTER);
        add(getRightContainer(), BorderLayout.EAST);
        add(getBottomContainer(), BorderLayout.SOUTH);
    }

    private JButton getStartGameButton() {
        JButton startGameButton = new JButton("Start");
        startGameButton.addActionListener(startu);
        return startGameButton;
    }

    //still might need refinement
    private JComboBox<ChompPlayer> getPlayerComboBox1() {
        JComboBox<ChompPlayer> playerComboBox = new JComboBox<>();
        for (Player player : player1s) {
            playerComboBox.addItem((ChompPlayer) player);
        }
        selectedPlayer1 = playerComboBox.getItemAt(0);
        playerComboBox.addActionListener(((e) -> {
            selectedPlayer1 = (ChompPlayer) playerComboBox.getSelectedItem();
        }));
        return playerComboBox;
    }

    private JComboBox<ChompPlayer> getPlayerComboBox2() {
        JComboBox<ChompPlayer> playerComboBox = new JComboBox<>();
        for (Player player : player2s) {
            playerComboBox.addItem((ChompPlayer) player);
        }
        selectedPlayer2 = playerComboBox.getItemAt(0);
        playerComboBox.addActionListener(((e) -> {
            selectedPlayer2 = (ChompPlayer) playerComboBox.getSelectedItem();
        }));
        return playerComboBox;
    }

    private JSpinner getConcussiveGamesTextField() {
        JSpinner concGames = new JSpinner();
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
        playerPane.add(getPlayerComboBox1());
        return playerPane;
    }

    private Container getRightContainer() {
        Container playerPane = new Container();
        playerPane.setLayout(new FlowLayout());
        playerPane.add(getPlayerComboBox2());
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