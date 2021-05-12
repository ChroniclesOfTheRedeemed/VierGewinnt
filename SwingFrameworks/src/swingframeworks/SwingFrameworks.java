/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingframeworks;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import swingframeworks.AddItemsFrame;
import swingframeworks.EasyFrame;
import swingframeworks.MainFrame;

/**
 *
 * @author Mike
 */
public class SwingFrameworks extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame display = new EasyFrame(new Container(), "show");
        display.getContentPane().setLayout(new FlowLayout());
        display.setContentPane(new JPanel());
        MainFrame myMain = new MainFrame();
        EasyFrame mainFrame = new EasyFrame(myMain, "title");
        AddItemsFrame addingFrame = new AddItemsFrame((C) -> {
            C.addMouseListener(new MouseListener() {
                int items = 0;
                @Override
                public void mouseClicked(MouseEvent e) {
                    C.setName(items++ + "");
                    myMain.setFocusComponent(C);
                    System.out.println("heyo" + C.toString());
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            if(myMain.getFocusedComponent() instanceof Container){
                ((Container) myMain.getFocusedComponent()).add(C);
            } else {
                display.getContentPane().add(C);
            }
            display.pack();
        });
        addingFrame.setLocation(300, 600);
        display.setLocation(500, 600);
        mainFrame.setLocation(500, 200);
        myMain.setFocusComponent(display.getContentPane());
    }

}
