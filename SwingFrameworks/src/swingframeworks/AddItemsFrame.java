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
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * __DATE__ , __TIME__
 *
 * @author Mike
 */
public class AddItemsFrame extends EasyFrame {

    public AddItemsFrame(AddComponentListener f) {
        super(new Container(), "Component Addon");
        setLayout(new FlowLayout());
        for (TestComponents testComponent : TestComponents.values()) {
            JButton complyingButton = new JButton(testComponent.name());
            complyingButton.addActionListener((e) -> {
                f.componentToBeAddedd(testComponent.factory.createComponent());
            });
            add(complyingButton);
        }
        pack();
        
        setPreferredSize(new Dimension(200, getContentPane().getHeight() * TestComponents.values().length));
        pack();
        
    }

}

interface AddComponentListener {

    void componentToBeAddedd(Component d);
}

enum TestComponents {
    Button(() -> {
        return new JButton("cuteButtin");
    }),
    Panel(() -> {
        return new JPanel();
    }),
    ColorChooser(() -> {
        return new JColorChooser(); 
    }),
    Label(() -> {
        return new JLabel("tiny Label");
    });
    public ComponentFactory factory;

    private TestComponents(ComponentFactory factory) {
        this.factory = factory;
    }
}

interface ComponentFactory {

    Component createComponent();
}
