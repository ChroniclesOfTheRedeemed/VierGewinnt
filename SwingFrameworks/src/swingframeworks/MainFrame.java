/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package swingframeworks;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**__DATE__ , __TIME__
 *
 * @author Mike
 */
public class MainFrame extends Container {

    JLabel concurrentComponentLabeler;
    Component mainComponent;
    
    
    public MainFrame(){
        setup();
    }
    
    public void setFocusComponent(Component myComponent){
        mainComponent = myComponent;
        //myComponent.toString().substring(0, myComponent.toString().indexOf("["))
        concurrentComponentLabeler.setText(myComponent.getClass().getSimpleName());
    }
    
    public Component getFocusedComponent(){
        return mainComponent;
    }
    
    private void setup(){
        setLayout(new FlowLayout());
        concurrentComponentLabeler = new JLabel("<nothing targeted>");
        add(concurrentComponentLabeler);
        
        LayoutManager[] layouts = {
            new FlowLayout(),
            new BorderLayout(),
            new GridLayout()
        };
        JComboBox<LayoutManager> layoutchooser = new JComboBox<>(layouts);
        layoutchooser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED && mainComponent instanceof Container){
                    System.out.println("Layout Changed");
                    ((Container) mainComponent).setLayout((LayoutManager) layoutchooser.getSelectedItem());
                    mainComponent.doLayout();
                }
            }
        });
        add(layoutchooser);
        
    }
}
