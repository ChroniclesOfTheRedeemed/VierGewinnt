/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VierGewinntPlayers;

import Interfaces.Game;

/**
 *
 * @author absea
 */
public class GameState {
    public Boolean[][] SpielFeld;
    public boolean player1turn = true;
    public Game.GameResult lastGameResult;
    public boolean gameInProgress = true;

    public GameState(Boolean[][] SpielFeld, Game.GameResult lastGameResult) {
        this.SpielFeld = SpielFeld;
        this.lastGameResult = lastGameResult;
        gameInProgress = false;
    }
    
    public GameState(Boolean[][] SpielFeld) {
        this.SpielFeld = SpielFeld;
    }

    public GameState() {
    }
    
    
    
}

