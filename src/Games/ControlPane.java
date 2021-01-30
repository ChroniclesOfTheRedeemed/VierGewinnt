/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Games;

import Interfaces.Player;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import Games.VierGewinnt.VierGewinntPlayer;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
abstract public class ControlPane extends Container {

    //Me too actually
    protected ActionListener startu;
    protected Player[] player1s;
    protected Player[] player2s;

    //KILL ME pliz
    protected Player selectedPlayer1;
    protected Player selectedPlayer2;
    protected int concussive = 1;

    public void buildUpContainer(Player[] possiblePlayer1s, Player[] possiblePlayer2s, ActionListener gameStartListener) {

        player1s = possiblePlayer1s;
        player2s = possiblePlayer2s;
        startu = gameStartListener;
        setupBasicPane();
    }

    abstract public void setupBasicPane();

    public Player getSelectedPlayer1() {
        return selectedPlayer1;
    }

    public Player getSelectedPlayer2() {
        return selectedPlayer2;
    }
    

    public int getConcussiveGames() {
        return concussive;
    }
}

class PrepaidSwinGontets {

    static public JButton getStartGameButton(ActionListener startListener) {
        JButton startGameButton = new JButton("Start");
        startGameButton.addActionListener(startListener);
        return startGameButton;
    }

    //still might need refinement
    static public JComboBox<Player> getPlayerComboBox1(ItemListener itemChange, Player... player1s) {
        JComboBox<Player> playerComboBox = new JComboBox<>();
        for (Player player : player1s) {
            playerComboBox.addItem(player);
        }
        playerComboBox.addItemListener(itemChange);
        return playerComboBox;
    }

    static public JComboBox<Player> getPlayerComboBox2(ItemListener itemChange, Player... player2s) {
        JComboBox<Player> playerComboBox = new JComboBox<>();
        for (Player player : player2s) {
            playerComboBox.addItem(player);
        }
        playerComboBox.addItemListener(itemChange);
        return playerComboBox;
    }

    static public JSpinner getConcussiveGamesTextField(Integer startValue, ChangeListener changeListener) {
        JSpinner concGames = new JSpinner();
        concGames.setValue(startValue);
        concGames.addChangeListener(changeListener);
        return concGames;
    }

    static public JLabel getVSLabel() {
        JLabel vsLabel = new JLabel("vs", SwingConstants.CENTER);
        vsLabel.setPreferredSize(new Dimension(50, 40));
        return vsLabel;
    }
}
