package com.project.components.panels;

import com.project.commons.Memory;
import com.project.components.HexTextField;
import com.project.utils.HexParser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * This is the class that contains MAR, MBR and PC
 */
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

    public static String getMAR(){
        return mar.getText();
    }
    public  static String getMBR(){
        return mbr.getText();
    }
    public static void setMBR(String text){
        mbr.setText(text);
    }
    public static void setMAR(String text){
        mar.setText(text);
    }


    /**
     * this method will increment PC and set values of MAR and MBR and IR
     */
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
        mar.setText("000");
        mar.setEditable(false);
        this.add(mar);
        btnMbr.addActionListener(ae -> {
            String RegisterVal = InputPanel.getRegisterInput();
            mbr.setText(RegisterVal);
        });
        btnPc.addActionListener(ae -> {
            String addressVal = InputPanel.getAddressInput();
            pc.setText(addressVal);
        });

        btnMar.addActionListener(ae -> {
            String addressVal = InputPanel.getAddressInput();
            mar.setText(addressVal);
        });
        this.add(btnMar);


    }

}
