package com.project.components.panels;

import com.project.utils.Assembler;
import com.project.commons.Memory;
import com.project.components.BinTextField;
import com.project.components.PopupHandler;
import com.project.instructions.InstructionParser;
import com.project.utils.HexParser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * The ControlPanel Class has all the buttons necessary to control the machine
 */
public class ControlPanel extends JPanel {
    Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    // Checkboxes for HAlt and Run
    static JCheckBox haltCheckBox = new JCheckBox("Halt");
    static JCheckBox runCheckBox = new JCheckBox("Run");
    static JCheckBox overflowCheckBox = new JCheckBox("Overflow");
    static JButton stPlusButton = new JButton("St+");
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


        // Buttons for control
        JButton storeButton = new JButton("Store");
//        JButton stPlusButton = new JButton("St+");
        JButton loadButton = new JButton("Load");


        JButton initCustomButton = new JButton("init Custom");
        initCustomButton.setBackground(Color.RED);
        PopupHandler popupfile = new PopupHandler(frame);


        initCustomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                // Show the file chooser dialog
                int returnValue = fileChooser.showOpenDialog(frame);

                // Check if a file was selected
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    Boolean isMemLoaded = Memory.loadMemory(selectedFile);
                    popupfile.showPopup("IPL file Loading", "Memory is loaded with IPL Successfully",
                            "Error Occured in loading memory", isMemLoaded);
                } else {
                    System.out.println("error loading file");
                }
            }
        });
        JButton assembleButton = new JButton("Assemble");
        assembleButton.setBackground(Color.BLUE);
        PopupHandler assembleFile = new PopupHandler(frame);

        assembleButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            // Show the file chooser dialog
            int returnValue = fileChooser.showOpenDialog(frame);

            // Check if a file was selected
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                Boolean isAssembled = null;
                try {
                    Assembler.assembleFromFile(selectedFile);
                    isAssembled = true;
                } catch (IOException ex) {
                    isAssembled = false;
                }
                assembleFile.showPopup("Loading Assembler file", "File Assembled Sucessfully", "Invalid Input file", isAssembled);
            } else {
                JOptionPane.showMessageDialog(frame, "No file Selected",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        this.add(initCustomButton);
        this.add(assembleButton);

        // Text field for SS
        JButton ssButton = new JButton("SS");
        ssButton.addActionListener(ae -> {
            RegisterPanel.incrementPC();
            InstructionParser.fromIR(ImmutablePanel.getIr());
        });
        this.add(ssButton);


        // Button for Run
        JButton runButton = new JButton("Run");
        runButton.addActionListener(ae -> {
            setRun(true);
            System.out.println("run is set to true");
            while (runCheckBox.isSelected()) {
                RegisterPanel.incrementPC();
                String irText = ImmutablePanel.getIr();
                InstructionParser.fromIR(irText);
            }
        });

        loadButton.addActionListener(ae -> {
            String marText = RegisterPanel.getMAR();
            RegisterPanel.setMBR(memory.getValue(marText));
        });
        storeButton.addActionListener(ae -> {
            memory.setValue(RegisterPanel.getMAR(), RegisterPanel.getMBR());
        });

        stPlusButton.addActionListener(ae -> {
            String curMar = RegisterPanel.getMAR();
            memory.setValue(curMar, RegisterPanel.getMBR());
            RegisterPanel.setMAR(HexParser.inttoHexString(Integer.parseInt(curMar, 16) + 1, 3));
        });

        // Add components to the bottom panel
        this.add(storeButton);
        this.add(stPlusButton);
        this.add(loadButton);
        this.add(runButton);
        this.add(haltCheckBox);
        this.add(runCheckBox);
    }
}



