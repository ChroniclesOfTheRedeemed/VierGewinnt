/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import Exceptions.GameStateException;
import Exceptions.InvalidMoveException;
import Exceptions.MoveNotAvailableException;
import Games.Chomp.ChompGame;
import Interface.GameParticipants;
import java.awt.Dimension;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class ChompSimulatedGame implements TempGame<Dimension> {

    private final String gameName;
    private final String nameOfPlayer1;
    private final String nameOfPlayer2;
    private final PlayerMadeMoveListener hex;
    private int movesDone = 0;
    
    public ChompSimulatedGame(String gameName, String nameOFPlayer1, String nameOfPlayer2, PlayerMadeMoveListener hek){
        this.gameName = gameName;
        this.nameOfPlayer1 = nameOFPlayer1;
        this.nameOfPlayer2 = nameOfPlayer2;
        hex = hek;
    }
    
    @Override
    public void enemyMadeMove(){
        movesDone++;
    }
    
    @Override
    public int getSpielFeldBreite() {
        return ChompGame.SpielFeldBreite;
    }

    @Override
    public int getSpielFeldHöhe() {
        return ChompGame.SpielFeldHöhe;
    }

    @Override
    public String ZugzuString(Dimension zug) {
        return ChompGame.ZugzuStringt(zug);
    }

    @Override
    public Dimension StringzuZug(String zugString) {
        return (Dimension) ChompGame.StringzuZugt(zugString);
    }

    @Override
    public String getNameOfPlayer(boolean player1) {
        if (player1) {
            return nameOfPlayer1;
        } else {
            return nameOfPlayer2;
        }
    }

    @Override
    public int movesDone() {
        return movesDone;
    }

    @Override
    public void setGameParticipants(GameParticipants s) throws GameStateException {//remeins
        System.out.println("Nothing happens when GameParticipants are set here.");
    }

    @Override
    public void playerMadeMove() throws GameStateException, InvalidMoveException, MoveNotAvailableException {
        hex.playerMadeMoveOmega();
        movesDone++;
    }

    @Override
    public void run() {
        System.out.println("Nothing happens if I run.");
    }

    @Override
    public String toString(){
        return gameName;
    }
}
