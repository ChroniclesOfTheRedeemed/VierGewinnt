/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MorpheusTut;

import java.awt.Button;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicArrowButton;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class Main extends JFrame {
    
    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            Main m = new Main();
            m.setVisible(true);
        });
    }
    
    public Main(){
        setTitle("Unsre GUI");
        setSize(200, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        //Button
        JButton button = new JButton("Ende");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        JButton dialogButton = new JButton("Dialog");
        dialogButton.addActionListener((e) -> {
            JOptionPane.showMessageDialog(getContentPane(), "You pressed the Button", "Click", JOptionPane.WARNING_MESSAGE);
        });
        
        //Menubar
     //   ImageIcon icon = new ImageIcon("C:\\Users\\Mike\\Documents\\My Games\\League Dump\\ezgif-1-ffc092758e7a.png");
        JMenuBar menu = new JMenuBar();
        JMenu datei = new JMenu("Datei");
        JMenu submenu = new JMenu("Submenu");
        JMenuItem item1 = new JMenuItem("ich bin im Sub");
        
        JMenuItem abo = new JMenuItem("Abonniert mich :D");
        abo.addActionListener((ActionEvent e) -> {
            Desktop desktop = Desktop.getDesktop();
            URL url = null;
            try {
                url = new URL("https://www.javatpoint.com/java-swing");
            } catch (MalformedURLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                desktop.browse(url.toURI());
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        submenu.add(item1);
        datei.add(abo);
        datei.addSeparator();
        datei.add(submenu);
        
        menu.add(datei);
        setJMenuBar(menu);
        
        //Layout
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);
//        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(button));
//        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(button));
        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(dialogButton));
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(dialogButton));
        gl.setAutoCreateContainerGaps(true);
        
        
    }
    
    
    
}
