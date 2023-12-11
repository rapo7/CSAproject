package com.project.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputDialog {
    private static JTextField userInputField;
    private static String userInput; // Variable to store user input

    static JDialog customDialog = new JDialog();

    public static JDialog createCustomDialog() {
        customDialog.setTitle("Input");
        customDialog.setLayout(new BorderLayout());

        // Create a panel for input components
        JPanel inputPanel = new JPanel();
        userInputField = new JTextField(4); // Assuming you want a regular text field
        inputPanel.add(userInputField);

        // Add the input panel to the custom dialog
        customDialog.add(inputPanel, BorderLayout.CENTER);

        // Create a button for user action (e.g., "OK" button)
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> handleOkButtonClick());

        // Add the "OK" button to the custom dialog
        customDialog.add(okButton, BorderLayout.SOUTH);

        // Set the custom dialog properties
        customDialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        customDialog.setSize(300, 150);

        return customDialog;
    }

    private static void handleOkButtonClick() {
        // Custom logic when "OK" button is clicked
        userInput = userInputField.getText();
        System.out.println("User entered: " + userInput);
        customDialog.dispose();
    }

    public static String getUserInput() {
        String res = userInput;
        // Check if the address has less than three characters
        if (res.length() < 4) {
            // Pad '0' to the left until the length is at least three
            res = String.format("%0" + (4 - res.length()) + "d%s", 0, res);
        }

        return res;
    }
}
