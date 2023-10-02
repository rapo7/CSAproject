package com.project.components;


import com.project.utils.HexParser;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.JTextField;

/**
 * Hex Input validator only allows Hex characters for the input
 */
public class HexTextField extends JTextField {
    private final int maxLength; // Maximum length allowed for the text field



    public HexTextField(int maxColumns) {
        this.maxLength = maxColumns;
        this.setColumns(maxColumns);

        this.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c != KeyEvent.VK_BACK_SPACE &&  !HexParser.isAllowedHex(c) || getText().length() >= maxLength ) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }
}
