package com.project.components.panels;

import com.project.components.HexTextField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ImmutablePanel extends JPanel {
    int padding = 10;
    Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
    JLabel irLabel = new JLabel("IR");
    static JTextField ir = new HexTextField(4);
    JLabel mfrLabel = new JLabel("MFR ");

    public static String getIr() {
        return ir.getText();
    }

    public static  void setIr(String ins) {
        ir.setText(ins);
    }

    public static String getMfr() {
        return mfr.getText();
    }

    public static void setMfr(String memoryfr) {
        mfr.setText(memoryfr);
    }


    static JTextField mfr = new HexTextField(1);


    public ImmutablePanel(Color bgcolor) {
        this.setLayout(new GridLayout(3, 2));
        Border controltitledBorder = BorderFactory.createTitledBorder("Immutable Panel");
        Border controlcompoundBorder = BorderFactory.createCompoundBorder(controltitledBorder, paddingBorder);
        this.setBorder(controlcompoundBorder);
        this.setBackground(bgcolor);

        //IR text field and label
        this.add(irLabel);
        ir.setText("0000");
        ir.setEditable(false);
        this.add(ir);


        //MFR text field and label
        this.add(mfrLabel);
        mfr.setText("0");
        mfr.setEditable(false);
        this.add(mfr);


    }
}
