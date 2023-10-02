package com.project.components.panels;

import com.project.commons.RegisterMap;
import com.project.components.HexTextField;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class has all the UI and logic for the GPR Panel component and the registers
 */
public class GPRPanel extends JPanel {
    int padding = 10;
    private Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
    private JLabel gpr0label = new JLabel("GPR0");
    private static JTextField gpr0textField = new HexTextField(4);
    private JButton gpr0btn = new JButton("LD");
    private JLabel gpr1label = new JLabel("GPR1");
    private static JTextField gpr1textField = new HexTextField(4);
    private JButton gpr1btn = new JButton("LD");
    private JLabel gpr2label = new JLabel("GPR2");
    private static JTextField gpr2textField = new HexTextField(4);
    private JButton gpr2btn = new JButton("LD");
    private JLabel gpr3label = new JLabel("GPR3");
    private static JTextField gpr3textField = new HexTextField(4);
    private JButton gpr3btn = new JButton("LD");
    private static final RegisterMap regmap = RegisterMap.getInstance();


    public static void setGpr0textField(String text) {

        gpr0textField.setText(text);
    }


    public static void setGpr1textField(String text) {
        gpr1textField.setText(text);
    }


    public static void setGpr2textField(String text) {
        gpr2textField.setText(text);
    }


    public static void setGpr3textField(String text) {
        gpr3textField.setText(text);
    }

    public GPRPanel(Color bgcolor) {


        int padding = 10;
        this.setLayout(new GridLayout(4, 3));
        this.setBorder(new EmptyBorder(padding, padding, padding, padding));
        gpr0textField.setText("0000");
        gpr1textField.setText("0000");
        gpr2textField.setText("0000");
        gpr3textField.setText("0000");
        gpr0textField.setEditable(false);
        gpr1textField.setEditable(false);
        gpr2textField.setEditable(false);
        gpr3textField.setEditable(false);

        gpr0btn.addActionListener(ae -> {
            String regInput = InputPanel.getRegisterInput();
            regmap.setValue("GPR0", regInput);
            gpr0textField.setText(regInput);
        });


        this.add(gpr0label);
        this.add(gpr0textField);
        this.add(gpr0btn);


        gpr1btn.addActionListener(ae -> {
            String regInput = InputPanel.getRegisterInput();
            regmap.setValue("GPR1", regInput);
            gpr1textField.setText(regInput);
        });
        this.add(gpr1label);
        this.add(gpr1textField);
        this.add(gpr1btn);


        gpr2btn.addActionListener(ae -> {
            String regInput = InputPanel.getRegisterInput();
            regmap.setValue("GPR2", regInput);
            gpr2textField.setText(regInput);
        });
        this.add(gpr2label);
        this.add(gpr2textField);
        this.add(gpr2btn);


        gpr3btn.addActionListener(ae -> {
            String regInput = InputPanel.getRegisterInput();
            regmap.setValue("GPR3", regInput);
            gpr3textField.setText(regInput);
        });
        this.add(gpr3label);
        this.add(gpr3textField);
        this.add(gpr3btn);




        Border gprtitledBorder = BorderFactory.createTitledBorder("GPR Panel");
        Border gprcompoundBorder = BorderFactory.createCompoundBorder(gprtitledBorder, paddingBorder);
        this.setBorder(gprcompoundBorder);
        this.setBackground(bgcolor);
    }




}
