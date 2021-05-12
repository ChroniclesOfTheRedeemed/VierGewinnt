/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swingframeworks;

import java.awt.Container;
import javax.swing.JFrame;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class EasyFrame extends JFrame {

    public EasyFrame(Container myPane, String title) {
        setupBasicPane(myPane, title);
    }

    private void setupBasicPane(Container myPane, String title) {
        setTitle(title);
        setSize(myPane.getPreferredSize());
        setLocationRelativeTo(null);
        setContentPane(myPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
