/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatecomb;

import Core.Game2D2P;
import Core.Game2D2PFactory;
import Core.Player;
import Core.VierGewinntSpiel;
import Games.Chomp.ChompGame;
import Interface.GameParticipants;
import OuterReferences.stuffWriter;
import Server.MyGameServer;
import TheBetterClient.ClientFrame;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
//Bugs:
//
// Starting a game with someone who is actually in game will force unpredicted behaviour. [BUGS]
//
//
//
//
//
public class UltiVars {

    public static ServerGUI myServer = new ServerGUI();
    public static void main(String args[]) throws IOException {

        MyGameServer myServer = new MyGameServer(new stuffWriter(), getSupportedGames());
        (new Thread(myServer)).start();
    }

    public static ArrayList<Game2D2PFactory> getSupportedGames() {
        ArrayList<Game2D2PFactory> result = new ArrayList<>();
        result.add(new Game2D2PFactory() {
            @Override
            public Game2D2P<Integer> createGame(ArrayList<Player> players) {
                return new VierGewinntSpiel(new GameParticipants(players.get(0), players.get(1), new ArrayList()));
            }

            @Override
            public String getName() {
                return VierGewinntSpiel.GameName;
            }
        });
        result.add(new Game2D2PFactory() {
            @Override
            public Game2D2P createGame(ArrayList<Player> players) {
                return new ChompGame(new GameParticipants(players.get(0), players.get(1), new ArrayList()));
            }

            @Override
            public String getName() {
                return ChompGame.GameName;
            }
        });
        return result;
    }
}
