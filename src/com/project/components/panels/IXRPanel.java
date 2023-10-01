package com.project.components.panels;

import com.project.commons.RegisterMap;
import com.project.components.HexTextField;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class IXRPanel extends JPanel {
    int padding = 10;
    private Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);


    private JLabel ixr1label = new JLabel("IXR1");
    private static JTextField ixr1textField = new HexTextField(4);
    private JButton ixr1btn = new JButton("LD");
    private JLabel ixr2label = new JLabel("IXR2");
    private static JTextField ixr2textField = new HexTextField(4);
    private JButton ixr2btn = new JButton("LD");
    private JLabel ixr3label = new JLabel("IXR3");
    private static JTextField ixr3textField = new HexTextField(4);
    private JButton ixr3btn = new JButton("LD");
    private static final RegisterMap regmap = RegisterMap.getInstance();






    public static void setIxr1textField(String text) {
        regmap.setValue("IXR1", text);
        ixr1textField.setText(text);
    }


    public static void setIxr2textField(String text) {
        regmap.setValue("IXR2", text);
        ixr2textField.setText(text);
    }


    public static void setIxr3textField(String text) {
        regmap.setValue("IXR3", text);
        ixr3textField.setText(text);
    }

    public IXRPanel(Color bgcolor) {


        int padding = 10;
        this.setLayout(new GridLayout(3, 3));
        this.setBorder(new EmptyBorder(padding, padding, padding, padding));
        ixr1textField.setText("0000");
        ixr2textField.setText("0000");
        ixr3textField.setText("0000");
        ixr1textField.setEditable(false);
        ixr2textField.setEditable(false);
        ixr3textField.setEditable(false);





        ixr1btn.addActionListener(ae -> {
            String regInput = InputPanel.getRegisterInput();
            regmap.setValue("IXR1", regInput);
            ixr1textField.setText(regInput);
        });
        this.add(ixr1label);
        this.add(ixr1textField);
        this.add(ixr1btn);


        ixr2btn.addActionListener(ae -> {
            String regInput = InputPanel.getRegisterInput();
            regmap.setValue("IXR2", regInput);
            ixr2textField.setText(regInput);
        });
        this.add(ixr2label);
        this.add(ixr2textField);
        this.add(ixr2btn);


        ixr3btn.addActionListener(ae -> {
            String regInput = InputPanel.getRegisterInput();
            regmap.setValue("IXR2", regInput);
            ixr2textField.setText(regInput);
        });
        this.add(ixr3label);
        this.add(ixr3textField);
        this.add(ixr3btn);




        Border ixrtitledBorder = BorderFactory.createTitledBorder("IXR Panel");
        Border ixrcompoundBorder = BorderFactory.createCompoundBorder(ixrtitledBorder, paddingBorder);
        this.setBorder(ixrcompoundBorder);
        this.setBackground(bgcolor);
    }




}
