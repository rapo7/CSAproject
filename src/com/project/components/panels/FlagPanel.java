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
 * The FlagPanel Class has all the buttons necessary to control the machine
 */
public class FlagPanel extends JPanel {
    Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
    // Checkboxes for HAlt and Run
    static JCheckBox divzeroCheckBox = new JCheckBox("DIVZRO");
    static JCheckBox overflowCheckbox = new JCheckBox("OVRFLW");

    static JCheckBox underFlowCheckBox = new JCheckBox("UNDRFLW");
    static JCheckBox equalOrNotCheckbox = new JCheckBox("EQL");


    public static void clearFlags(){
        overflowCheckbox.setSelected(false);
        underFlowCheckBox.setSelected(false);
        divzeroCheckBox.setSelected(false);
        equalOrNotCheckbox.setSelected(false);
    }
    public static void setOverflow(boolean x) {
        overflowCheckbox.setSelected(x);
    }

    public static void setDivZero(boolean x) {
        divzeroCheckBox.setSelected(x);
    }

    public static void setUnderflow(boolean x) {
        underFlowCheckBox.setSelected(x);
    }

    public static void setEqualOrNot(boolean x) {
        equalOrNotCheckbox.setSelected(x);
    }


    MouseListener[] ml = overflowCheckbox.getListeners(MouseListener.class);
    MouseListener[] hml = divzeroCheckBox.getListeners(MouseListener.class);
    MouseListener[] uml = underFlowCheckBox.getListeners(MouseListener.class);
    MouseListener[] eml = equalOrNotCheckbox.getListeners(MouseListener.class);


    public FlagPanel(Color bgcolor) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        Border controltitledBorder = BorderFactory.createTitledBorder("Flag Panel");
        Border controlcompoundBorder = BorderFactory.createCompoundBorder(controltitledBorder, paddingBorder);
        this.setBorder(controlcompoundBorder);
        this.setBackground(bgcolor);


        for (MouseListener listener : ml) overflowCheckbox.removeMouseListener(listener);
        for (MouseListener mouseListener : hml) divzeroCheckBox.removeMouseListener(mouseListener);
        for (MouseListener mouseListener : uml) underFlowCheckBox.removeMouseListener(mouseListener);
        for (MouseListener mouseListener : eml) equalOrNotCheckbox.removeMouseListener(mouseListener);

        InputMap oim = overflowCheckbox.getInputMap();
        oim.put(KeyStroke.getKeyStroke("SPACE"), "none");
        oim.put(KeyStroke.getKeyStroke("released SPACE"), "none");

        InputMap dim = divzeroCheckBox.getInputMap();
        dim.put(KeyStroke.getKeyStroke("SPACE"), "none");
        dim.put(KeyStroke.getKeyStroke("released SPACE"), "none");

        InputMap uim = underFlowCheckBox.getInputMap();
        uim.put(KeyStroke.getKeyStroke("SPACE"), "none");
        uim.put(KeyStroke.getKeyStroke("released SPACE"), "none");

        InputMap eim = equalOrNotCheckbox.getInputMap();
        eim.put(KeyStroke.getKeyStroke("SPACE"), "none");
        eim.put(KeyStroke.getKeyStroke("released SPACE"), "none");


        this.add(divzeroCheckBox);
        this.add(overflowCheckbox);
        this.add(underFlowCheckBox);
        this.add(equalOrNotCheckbox);
    }
}



