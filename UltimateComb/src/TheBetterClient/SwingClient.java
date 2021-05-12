/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheBetterClient;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
import extra.LoginPanel;
import static Server.ServerLogs.General.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class SwingClient extends Socket {

    public SwingClient() throws IOException {
        super(IP, Port);
        //EasyDialog d = new EasyDialog(myPane, "SwingClient");
    }

    public static void main(String[] args) throws IOException {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ClientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        SwingClient client = new SwingClient();
        try {
            client.test();
        } catch (IOException e) {
        }
    }

    LoginPanel unlogged;
    ClientFrame logged;

    void test() throws IOException {

        unlogged = new LoginPanel(this, (String name) -> {
            try {
                log(name);
            } catch (IOException ex) {
                Logger.getLogger(SwingClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        unlogged.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        unlogged.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    System.out.println("Window closed");
                    close();
                } catch (IOException ex) {
                    Logger.getLogger(SwingClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    void log(String name) throws IOException {
        unlogged.dispose();

        logged = new ClientFrame(this, () -> {
            try {
                logged.dispose();
                test();
            } catch (IOException ex) {
                Logger.getLogger(SwingClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }, name);

        logged.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        logged.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    logged.logOutFromClient();
                    System.out.println("Window closed");
                    close();
                } catch (IOException ex) {
                    Logger.getLogger(SwingClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }
}
