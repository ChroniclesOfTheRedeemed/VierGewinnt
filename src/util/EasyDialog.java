/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.awt.Container;
import javax.swing.JDialog;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */

/** Ok to not make this more complicated than it is, the idea will be the following:
 * Obviously one of these 5 connected classes has to be instanciated, and it does
 * technically not matter which. By Design it would be more fitting though if, the 
 * Player (The specific interface is getting instanciated). This will give more
 * intelligent hold of future player implemention inclusions and makes a 
 * 
 * 
 * @author Mike
 */
public class EasyDialog extends JDialog {

    public EasyDialog(Container myPane, String title) {
        setupBasicPane(myPane, title);
    }

    private void setupBasicPane(Container myPane, String title) {
        setTitle(title);
        setSize(myPane.getPreferredSize());
        setLocationRelativeTo(null);
        setContentPane(myPane);
    }
}
