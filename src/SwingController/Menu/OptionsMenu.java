/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SwingController.Menu;

import Interfaces.GameWatcher;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class OptionsMenu extends JMenu{
    
    private SpectatorMenu specSubmenu;
    private boolean shoudRecord;

    public OptionsMenu(ArrayList<GameWatcher> spectators) {
        super("Options");
        setupMenu(spectators);
    }
    
    public boolean shouldRecord() {
        return shoudRecord;
    }
    
    public ArrayList<GameWatcher> getSelectedSpectators() {
       return specSubmenu.getSelectedSpectators();
    }

    private void setupMenu(ArrayList<GameWatcher> spectators) {
        specSubmenu = new SpectatorMenu("spectators", spectators);
        this.add(specSubmenu);
    }
}

class SpectatorMenu extends JMenu {

    private boolean[] selection;
    private final ArrayList<GameWatcher> spectators;
    
    public SpectatorMenu(String text, ArrayList<GameWatcher> spectators) {
        super(text);
        this.spectators = spectators;
        setupMenu();
    }

    public ArrayList<GameWatcher> getSelectedSpectators() {
        ArrayList<GameWatcher> result = new ArrayList<>();
        for (int i = 0; i < selection.length; i++) {
            if (selection[i]) {
                result.add(spectators.get(i));
            }
        }
        return result;
    }
    
    private void setupMenu() {
        JCheckBoxMenuItem[] choosableSpectators = new JCheckBoxMenuItem[spectators.size()];
        selection = new boolean[choosableSpectators.length];

        for (int i = 0; i < choosableSpectators.length; i++) {
            choosableSpectators[i] = new JCheckBoxMenuItem(spectators.get(i).toString());
            selection[i] = choosableSpectators[i].isSelected();
            final int leckMich = i;
            choosableSpectators[i].addActionListener((ActionEvent e) -> {
                selection[leckMich] = choosableSpectators[leckMich].isSelected();
            });
            this.add(choosableSpectators[i]);
        }
    }
}
