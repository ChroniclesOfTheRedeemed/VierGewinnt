/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PlayerInterface.Bash;

import Interfaces.Game;
import Interfaces.InputListener;
import java.io.IOException;
import java.util.Scanner;
import PlayerInterface.VierGewinntFeld;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class BashField implements VierGewinntFeld{

    private final char Player1Mark = 'O';
    private final char Player2Mark = 'X';
    private final char Unoccupied = ' ';
    
    char[][] field;
    Scanner myScanner;
    int Rows, Coloumns;
    
    public BashField(int rows, int coloumns){
        field = new char[rows][coloumns];
        myScanner = new Scanner(System.in);
        Rows = rows;
        Coloumns = coloumns;
    }
    
    @Override
    public void resetField() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException ex) {
            System.out.println("unlucky");
        } catch (InterruptedException ex) {
            System.out.println("sprich mich doch an ;)");
        }
    }

    @Override
    public void updateMoveOnField(Integer move, boolean player1turn) {
        for (int row = Rows - 1; row >= 0; row--) {
            if (fieldUnoccupied(row, move)) {
                if (player1turn != Game.Player1hasFirstMove) {
                    field[row][move] = Player1Mark;
                } else {
                    field[row][move] = Player2Mark;
                }
                break;
            }
        }
    }
    
    

    @Override
    public void showResultOnField(Game.GameResult gameResult) {
        switch(gameResult){
            case Draw:
                System.out.println("Draw!");
                break;
            case GameWonForPlayer1:
                System.out.println("Player 1 Won!");
                break;
            case GameWonForPlayer2:
                System.out.println("Player 2 Won!");
                break;
        }
    }

    @Override
    public void setInputListener(InputListener inputGiven) {
        
    }

    private boolean fieldUnoccupied(int row, int coloumn) {
        return field[row][coloumn] == Unoccupied;
    }
    
    private void updateField(){
        resetField();
        System.out.println("");
        for (char[] row : field) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println("");
        }
    }
}
