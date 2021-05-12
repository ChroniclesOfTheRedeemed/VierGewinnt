/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.util.ArrayList;

/**
 *
 * @author Mike
 */
public interface LoggedClientRequestListener {

    void clientLog(String name, boolean in);

    void clientsLog(ArrayList<String> loggedClients);

    void otherAnswer(String answer, ArrayList<String> message);

    void serverResponse(String answer);

    void startGame(String gameName, String nameOfPlayer1, String nameOfPlayer2);

    void makeMove(String enemyMove);

    void getMove();

    void gameEnded(String finishingMove, String gameReslutName);

    void moveSet(String Move);

    void invite(String gameName, ArrayList<String> otherPlayers);

    void uninvite(String gameName, ArrayList<String> otherPlayers);
}
