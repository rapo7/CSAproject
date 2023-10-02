package com.project.components.panels;

import com.project.components.HexTextField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * This panel takes input and can be loaded to register of choice from this panel
 */
public class InputPanel extends JPanel {
    int padding = 10;
    Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
    static JTextField AddressInput = new HexTextField(3);
    static JTextField RegisterInput = new HexTextField(4);


    public static String getAddressInput(){
        return AddressInput.getText();
    }
    public static String getRegisterInput(){
        return RegisterInput.getText();
    }

    public InputPanel() {

        this.setLayout(new GridLayout(2, 2));
        Border gentitledBorder = BorderFactory.createTitledBorder("Input Panel");
        Border gencompoundBorder = BorderFactory.createCompoundBorder(gentitledBorder, paddingBorder);
        this.setBorder(gencompoundBorder);

        // Add Address text field and label
        this.add(new JLabel("Address Input "));
        AddressInput.setText("000");
        this.add(AddressInput);

        // Add Register Input Label and Text
        this.add(new JLabel("Register Input "));
        RegisterInput.setText("0000");
        this.add(RegisterInput);


    }

}
