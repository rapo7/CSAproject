package com.project.components.panels;

import com.project.commons.Memory;
import com.project.components.BinTextField;
import com.project.components.PopupHandler;
import com.project.instructions.InstructionParser;
import com.project.utils.HexParser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseListener;

public class ControlPanel extends JPanel {
    Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    // Checkboxes for HAlt and Run
    static JCheckBox haltCheckBox = new JCheckBox("Halt");
    static JCheckBox runCheckBox = new JCheckBox("Run");
    static JTextField operationText = new BinTextField(6);
    static JTextField gprTextField = new BinTextField(2);
    static JTextField ixrTextField = new BinTextField(2);
    static JTextField iTextField = new BinTextField(1);
    static JTextField addressTextField = new BinTextField(5);
    static  JButton stPlusButton = new JButton("St+");
    static Memory memory = Memory.getInstance();

    public static void setHalt(boolean x) {
        if (!x) {
            runCheckBox.setSelected(true);
            haltCheckBox.setSelected(false);
        } else {
            runCheckBox.setSelected(false);
            haltCheckBox.setSelected(true);
        }
    }

    public static void setRun(boolean x) {
        if (!x) {
            runCheckBox.setSelected(false);
            haltCheckBox.setSelected(true);
        } else {
            runCheckBox.setSelected(true);
            haltCheckBox.setSelected(false);
        }
    }

    MouseListener[] ml = runCheckBox.getListeners(MouseListener.class);
    MouseListener[] hml = haltCheckBox.getListeners(MouseListener.class);





    public ControlPanel(Color bgcolor, JFrame frame) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        Border controltitledBorder = BorderFactory.createTitledBorder("Control Panel");
        Border controlcompoundBorder = BorderFactory.createCompoundBorder(controltitledBorder, paddingBorder);
        this.setBorder(controlcompoundBorder);
        this.setBackground(bgcolor);

        haltCheckBox.setSelected(true);

        for (MouseListener listener : ml) runCheckBox.removeMouseListener(listener);
        for (MouseListener mouseListener : hml) haltCheckBox.removeMouseListener(mouseListener);

        InputMap im = runCheckBox.getInputMap();
        im.put(KeyStroke.getKeyStroke("SPACE"), "none");
        im.put(KeyStroke.getKeyStroke("released SPACE"), "none");

        InputMap him = haltCheckBox.getInputMap();
        him.put(KeyStroke.getKeyStroke("SPACE"), "none");
        him.put(KeyStroke.getKeyStroke("released SPACE"), "none");


        // Add components to the bottom panel
        // TODO Operation Field
//        this.add(new JLabel("Operation"));
//        this.add(operationText);
//
//
//        //GPR field
//        this.add(new JLabel("GPR"));
//        this.add(gprTextField);
//
//        //IXR Field
//        this.add(new JLabel("IXR"));
//        this.add(ixrTextField);
//
//        //I Field
//        this.add(new JLabel("I"));
//        this.add(iTextField);
//
//        //Address Field
//        this.add(new JLabel("Address"));
//        this.add(addressTextField);


        // Buttons for control
        JButton storeButton = new JButton("Store");
//        JButton stPlusButton = new JButton("St+");
        JButton loadButton = new JButton("Load");
        JButton initButton = new JButton("init");
        initButton.setBackground(Color.RED);
        PopupHandler popup = new PopupHandler(frame);

        initButton.addActionListener(ae -> popup.showPopup());

        this.add(initButton);

        // Text field for SS
        JButton ssButton = new JButton("SS");
        ssButton.addActionListener(ae -> {
            InstructionParser.fromIR(ImmutablePanel.getIr());
            RegisterPanel.incrementPC();
        });
        this.add(ssButton);


        // Button for Run
        JButton runButton = new JButton("Run");
        runButton.addActionListener(ae -> {
            setRun(true);
            System.out.println("run is set to true");
            while (runCheckBox.isSelected()){
                RegisterPanel.incrementPC();
                String irText = ImmutablePanel.getIr();
                InstructionParser.fromIR(irText);
            }
        });

        loadButton.addActionListener(ae -> {
            String marText = RegisterPanel.getMAR();
            RegisterPanel.setMBR( memory.getValue(marText));
        });
        storeButton.addActionListener(ae -> {
            memory.setValue(RegisterPanel.getMAR(), RegisterPanel.getMBR());
        });

        stPlusButton.addActionListener(ae -> {
            String curMar = RegisterPanel.getMAR();
            memory.setValue(curMar, RegisterPanel.getMBR());
            RegisterPanel.setMAR(HexParser.inttoHexString(Integer.parseInt(curMar, 16)+ 1, 3));
        });
        // Add components to the bottom panel
        this.add(storeButton);
        this.add(stPlusButton);
        this.add(loadButton);
        this.add(initButton);
        this.add(runButton);
        this.add(haltCheckBox);
        this.add(runCheckBox);
    }
}



