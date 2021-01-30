/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 * This Exception will be thrown by the Player if the Game tries to access a move from the player
 * despite him not having one. 
 * 
 * This Exception takes care of a faulty call of the playerMadeMove function.
 * 
 * @author Mike
 */
public class MoveNotAvailableException extends Exception{}