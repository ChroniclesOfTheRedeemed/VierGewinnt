/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayerInterface.Swing;

import Exceptions.InvalidMoveException;
import Interfaces.Game;
import Interfaces.InputListener;
import PlayerInterface.ChompField;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 * possible extension function: revert move according to gamerule (incorrect
 * move)
 *
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class ChompSwingPane extends Container implements ChompField {

    private final int Rows, Coloumns; //technically redundant
    private final Color PLAYER1COLOR = Color.BLUE;
    private final Color PLAYER2COLOR = Color.GREEN;
    private final Color UNOCCUPIEDCOLOR = Color.LIGHT_GRAY;
    
    private JButton[][] buttonField;    //matrix element access

    public ChompSwingPane(int preferedWidth, int rows, int coloumns) {
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
    public void updateMoveOnField(Dimension checkedMove, boolean player1turn) {
        enableUserInput(true);
        for (int width = checkedMove.width; width < Coloumns; width++) {
            if (fieldUnoccupied(checkedMove.height, width)) {
                for (int height = checkedMove.height; height < Rows; height++) {
                    if (fieldUnoccupied(height, width)) {
                        if (player1turn != Game.Player1hasFirstMove) {
                            buttonField[height][width].setBackground(PLAYER1COLOR);
                        } else {
                            buttonField[height][width].setBackground(PLAYER2COLOR);
                        }
                    } else {
                        break;
                    }
                }
            } else {
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
            for (int row = 0; row < Rows; row++) {
            final int Coloumn = coloumn;
            final int Row = row;
            buttonField[row][coloumn].addActionListener((ActionEvent e) -> {
                enableUserInput(false);
                try {
                    inputGiven.inputGiven(new Dimension(Coloumn, Row));
                } catch (InvalidMoveException ex) {
                    System.err.println("Manual Error ChompPane");
                }
            });
            }
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
        for (JButton[] buttons : buttonField) {
            for (JButton button : buttons) {
                button.setEnabled(enable);
            }
        }
    }

    private void setupPane(int preferedWidth) {
        this.setPreferredSize(new Dimension(preferedWidth, preferedWidth * (Rows + 1) / Coloumns));
        this.setLayout(new GridLayout(Rows, Coloumns));

        addFeldButtons();
        enableUserInput(false);
    }

    private void addFeldButtons() {
        buttonField = new JButton[Rows][Coloumns];
        for (JButton[] row : buttonField) {
            for (int coloumn = 0; coloumn < Coloumns; coloumn++) {
                row[coloumn] = new JButton();
                row[coloumn].setEnabled(false);
                this.add(row[coloumn]);
            }
        }
    }
}
