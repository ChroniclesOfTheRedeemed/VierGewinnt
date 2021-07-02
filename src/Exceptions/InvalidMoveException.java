/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;


/**
 * If this Exception is thrown, a move on the Spielfeld has been deemed unqualified for the purpose 
 * of this game.
 * 
 * @author Mike
 */
public class InvalidMoveException extends Exception {

    final int move;

    public InvalidMoveException(int move) {
        this.move = move;
    }

    public String message() {
        return move + " is not a valid move, corresponding with the setup boundaries.";
    }

}