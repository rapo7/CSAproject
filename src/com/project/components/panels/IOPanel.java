package com.project.components.panels;

import com.project.components.HexTextField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * This panel takes input and can be loaded to register of choice from this panel
 */
public class IOPanel extends JPanel {
    int padding = 10;
    Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
    static JTextField OutInput = new HexTextField(4);



    public static void setOutput(String text){
         OutInput.setText(text);
    }

    public IOPanel() {

        this.setLayout(new GridLayout(1, 2));
        Border gentitledBorder = BorderFactory.createTitledBorder("IO Panel");
        Border gencompoundBorder = BorderFactory.createCompoundBorder(gentitledBorder, paddingBorder);
        this.setBorder(gencompoundBorder);

        // Add Register Input Label and Text
        this.add(new JLabel("OUT "));
        this.add(OutInput);


    }

}
