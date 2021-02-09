/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayerInterface.Swing;

import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import Interfaces.Game;
import Interfaces.InputListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import PlayerInterface.VierGewinntFeld;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * possible extension function: revert move according to gamerule (incorrect
 * move)
 *
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class VierGewinntPane extends Container implements VierGewinntFeld {

    private final int Rows, Coloumns; //technically redundant
    private final Color PLAYER1COLOR = Color.BLUE;
    private final Color PLAYER2COLOR = Color.GREEN;
    private final Color UNOCCUPIEDCOLOR = Color.LIGHT_GRAY;

    private JButton[] insertionButtons;
    private JButton[][] buttonField;    //matrix element access

    public VierGewinntPane(int preferedWidth, int rows, int coloumns) {
        this.Rows = rows;
        this.Coloumns = coloumns;
        setupPane(preferedWidth);
    }

    @Override
    public void resetField() {
        enableUserInput(true);
        for (JButton[] row : buttonField) {
            for (JButton cell : row) {
                cell.setBackground(UNOCCUPIEDCOLOR);
            }
        }
    }

    @Override
    public void updateMoveOnField(Integer move, boolean player1turn) {
        enableUserInput(true);
        for (int row = Rows - 1; row >= 0; row--) {
            if (fieldUnoccupied(row, move)) {
                if (player1turn != Game.Player1hasFirstMove) {
                    buttonField[row][move].setBackground(PLAYER1COLOR);
                } else {
                    buttonField[row][move].setBackground(PLAYER2COLOR);
                }
                break;
            }
        }
    }

    @Override
    public void showResultOnField(Game.GameResult gameResult) {
        enableUserInput(false);
        System.out.println(gameResult);
    }

    @Override
    public void setInputListener(InputListener inputGiven) {
        for (int coloumn = 0; coloumn < Coloumns; coloumn++) {
            final int finalint = coloumn;
            insertionButtons[coloumn].addActionListener((ActionEvent e) -> {
                enableUserInput(false);
                try {
                    inputGiven.inputGiven(finalint);
                } catch (InvalidMoveException ex) {
                    System.err.println("Manual Error ViergewinntPane");
                } catch (GameStateException ex) {
                    Logger.getLogger(VierGewinntPane.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    @Override
    public String toString() {
        return "ContainerPlayer";
    }

    private boolean fieldUnoccupied(int row, int coloumn) {
        return buttonField[row][coloumn].getBackground().equals(UNOCCUPIEDCOLOR);
    }

    private void enableUserInput(boolean enable) {
        for (JButton insertionButton : insertionButtons) {
            insertionButton.setEnabled(enable);
        }
    }

    private void setupPane(int preferedWidth) {
        this.setPreferredSize(new Dimension(preferedWidth, preferedWidth * (Rows + 1) / Coloumns));
        this.setLayout(new GridLayout(Rows + 1, Coloumns));

        addInsertionButtons();
        addFeldButtons();
        enableUserInput(false);
    }

    private void addInsertionButtons() {
        insertionButtons = new JButton[Coloumns];
        for (int coloumn = 0; coloumn < Coloumns; coloumn++) {
            insertionButtons[coloumn] = new JButton();
            this.add(insertionButtons[coloumn]);
        }
    }

    private void addFeldButtons() {
        buttonField = new JButton[Rows][Coloumns];
        for (JButton[] row : buttonField) {
            for (int coloumn = 0; coloumn < row.length; coloumn++) {
                row[coloumn] = new JButton();
                row[coloumn].setEnabled(false);
                this.add(row[coloumn]);
            }
        }
    }
}
