package com.project.components.panels;

import com.project.commons.Memory;
import com.project.components.BinTextField;
import com.project.components.PopupHandler;
import com.project.instructions.InstructionParser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    public ControlPanel(Color bgcolor, JFrame frame) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        Border controltitledBorder = BorderFactory.createTitledBorder("Control Panel");
        Border controlcompoundBorder = BorderFactory.createCompoundBorder(controltitledBorder, paddingBorder);
        this.setBorder(controlcompoundBorder);
        this.setBackground(bgcolor);


        // Add components to the bottom panel
        //Operation Field
        this.add(new JLabel("Operation"));
        JTextField operationText = new BinTextField(6);
        this.add(operationText);


        //GPR field
        this.add(new JLabel("GPR"));
        JTextField gprTextField = new BinTextField(2);
        this.add(gprTextField);

        //IXR Field
        this.add(new JLabel("IXR"));
        JTextField ixrTextField = new BinTextField(2);
        this.add(ixrTextField);

        //I Field
        this.add(new JLabel("I"));
        JTextField iTextField = new BinTextField(1);
        this.add(iTextField);

        //Address Field
        this.add(new JLabel("Address"));
        JTextField addressTextField = new BinTextField(5);
        this.add(addressTextField);


        // Buttons for control
        JButton storeButton = new JButton("Store");
//        JButton stPlusButton = new JButton("St+");
        JButton loadButton = new JButton("Load");
        JButton initButton = new JButton("init");
        initButton.setBackground(Color.RED);
        PopupHandler popup = new PopupHandler(frame);

        initButton.addActionListener(ae -> {
            popup.showPopup();
        });

        this.add(initButton);

        // Text field for SS
        JButton ssButton = new JButton("SS");
        ssButton.addActionListener(ae -> {
            String opcode = operationText.getText();
            String reg = gprTextField.getText();
            String ireg = ixrTextField.getText();
            String ibit = iTextField.getText();
            String address = addressTextField.getText();
            InstructionParser.parse(opcode, reg, ireg, ibit, address);
            RegisterPanel.incrementPC();
        });
        this.add(ssButton);


        // Button for Run
        JButton runButton = new JButton("Run");
        runButton.addActionListener(ae -> {
            String opcode = operationText.getText();
            String reg = gprTextField.getText();
            String ireg = ixrTextField.getText();
            String ibit = iTextField.getText();
            String address = addressTextField.getText();

            InstructionParser.parse(opcode, reg, ireg, ibit, address);
            RegisterPanel.incrementPC();
        });


        // Checkboxes for HAlt and Run
        JCheckBox haltCheckBox = new JCheckBox("Halt");
        JCheckBox runCheckBox = new JCheckBox("Run");

        // Add components to the bottom panel
        this.add(storeButton);
        //TODO
//        this.add(stPlusButton);
        this.add(loadButton);
        this.add(initButton);
        this.add(runButton);
        this.add(haltCheckBox);
        this.add(runCheckBox);
    }
}



