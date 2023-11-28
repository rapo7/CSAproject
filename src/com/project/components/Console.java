package com.project.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Console extends JPanel {
    public Console() {
        Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Color consoleColor = new Color(255, 255, 255);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        Border controltitledBorder = BorderFactory.createTitledBorder("Control Panel");
        Border controlcompoundBorder = BorderFactory.createCompoundBorder(controltitledBorder, paddingBorder);
        this.setBorder(controlcompoundBorder);
        this.setBackground(consoleColor);
    }
}
