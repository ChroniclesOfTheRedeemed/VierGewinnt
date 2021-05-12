/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TheBetterClient;

import static Server.ServerLogs.GameVars.*;
import static Server.ServerLogs.General.*;
import static Server.ServerLogs.LoggedClientResponses.*;
import static Server.ServerLogs.readFromSocket;
import extra.LoggedClientRequestListener;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class ServerInputLeaf implements Runnable{

    private final Socket myClient;
    private final LoggedClientRequestListener myListener;

    public ServerInputLeaf(Socket socket, LoggedClientRequestListener listener) {
        myListener = listener;
        myClient = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                listen();
            } catch (IOException ex) {
                Logger.getLogger(ServerInputLeaf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void listen() throws IOException {
        //checkServerStatus
        ArrayList<String> message = readFromSocket(myClient);
        String command = message.get(0);
        message.remove(0);
        switch (command) {
            case TargetAdded:
            case TargetRemoved:
            case RequestUnderstood:
            case RequestNotUnderstood:
                myListener.serverResponse(command);
                break;
            case RequestLoggedClients:
                myListener.clientsLog(message);
                break;
            case GameStarted:
                
                myListener.startGame(message.get(0), message.get(1), message.get(2));
                break;
            case MakeMove:
                myListener.makeMove(message.get(0));
                break;
            case GetMove:
                myListener.getMove();
                break;
            case GameEnded:
               myListener.gameEnded(message.get(0), message.get(1));
                break;
            case MoveSet:
                myListener.moveSet(message.get(0));
                break;
            case RequestGame:
                String name = message.get(0);
                message.remove(0);
                myListener.invite(name, message);
                break;
            case DeclineGame:
                String namea = message.get(0);
                message.remove(0);
                myListener.uninvite(namea, message);
                break;
            default:
                if (command.endsWith(ClientDisconnectedSuffix)) {
                    String clientName = command.substring(0, command.length() - ClientDisconnectedSuffix.length());
                    myListener.clientLog(clientName, false);
                } else if (command.endsWith(ClientConnectedSuffix)) {
                    String clientName = command.substring(0, command.length() - ClientConnectedSuffix.length());
                    myListener.clientLog(clientName, true);
                }

                myListener.otherAnswer(command, message);
        }
    }
}
