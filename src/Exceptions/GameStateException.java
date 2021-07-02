/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 * If this Exception is thrown, the current status of the game is not supported by the called function.
 * 
 * This Exception is usually thrown when you try to start a game during an existing one
 * or when you try to interact with a non existing game.
 * 
 * @author Mike
 */
public class GameStateException extends Exception {}
