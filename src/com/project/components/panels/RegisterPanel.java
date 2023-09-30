package com.project.components.panels;

import com.project.commons.Memory;
import com.project.components.HexTextField;
import com.project.utils.HexParser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class RegisterPanel extends JPanel {
    int padding = 10;
    Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
    static Memory memory = Memory.getInstance();
    static JTextField pc = new HexTextField(3);
    static JTextField mbr = new HexTextField(4);
    static JTextField mar = new HexTextField(3);
    static JButton btnMbr = new JButton("LD");
    static JButton btnMar = new JButton("LD");
    static JButton btnPc = new JButton("LD");

    public static void incrementPC() {
        int marVal = Integer.parseInt(pc.getText(), 16);
        String newPC = HexParser.inttoHexString(marVal + 1 , 3);
        String newMAR =   HexParser.inttoHexString(marVal, 3);
        String newMBR = memory.getValue(newMAR);
        pc.setText(newPC);
        mar.setText(newMAR);
        mbr.setText(newMBR);

        ImmutablePanel.setIr(newMBR);
    }


    public RegisterPanel(Color bgcolor) {

        this.setLayout(new GridLayout(3, 3));
        Border gentitledBorder = BorderFactory.createTitledBorder("Register Panel");
        Border gencompoundBorder = BorderFactory.createCompoundBorder(gentitledBorder, paddingBorder);
        this.setBorder(gencompoundBorder);
        this.setBackground(bgcolor); // Set background color

        // Add PC text field and label
        this.add(new JLabel("PC "));
        pc.setText("000");
        pc.setEditable(false);
        this.add(pc);
        this.add(btnPc);


        //Add MBR text field and label
        this.add(new JLabel("MBR "));
        mbr.setText("0000");
        mbr.setEditable(false);
        this.add(mbr);
        this.add(btnMbr);

        //Add MAR text field and label
        this.add(new JLabel("MAR "));
        mar.setText("001");
        mar.setEditable(false);
        this.add(mar);

        btnMar.addActionListener(ae -> {
            System.out.println(ae.getActionCommand());
        });
        this.add(btnMar);


    }

}
