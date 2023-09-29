package com.project;

import com.project.components.panels.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class MainScreen extends JFrame {

    Color primaryColor = new Color(129, 237, 183);  // Replace with your RGB values
    Color secondaryColor = new Color(174, 238, 238);
    Color accentColor = new Color(245, 222, 179);
    Color paleYellowColor = new Color(255, 255, 204);
    Color pink = new Color(233, 166, 253);



    public MainScreen() {
        super("Basic Computer with 4 Registers");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // an empty border with padding (10 pixels on all sides)


        // Calculate the dimensions for 3/4th of the screen's width and height
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int targetWidth = screenWidth * 3 / 4;
        int targetHeight = screenHeight * 3 / 4;
        setSize(targetWidth, targetHeight);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        // Create a top panel for padding
        JPanel topPaddingPanel = new JPanel();


        // Create the main panel with a GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        int padding = 10;
        mainPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Create the GPR panel for GPR inputs with a GridLayout
        GPRPanel leftPanel= new GPRPanel(primaryColor);
        // Set background color

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(leftPanel);

        // Create the IXR panel below the GPR panel
        JPanel ixrPanel = new IXRPanel(secondaryColor);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(ixrPanel, gbc);
        
        // Create the right panel for additional text fields with a GridLayout
        JPanel regPanel = new RegisterPanel(accentColor);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1; // Span two rows
        mainPanel.add(regPanel, gbc);

        JPanel immutablePanel = new ImmutablePanel(pink);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1; // Span two rows
        mainPanel.add(immutablePanel, gbc);




        // Create the bottom panel with FlowLayout
        JPanel bottomPanel = new ControlPanel(paleYellowColor, this);

        // Add the top padding, main panel, and bottom panel to the JFrame
        add(topPaddingPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
        
        
    }


    

}

