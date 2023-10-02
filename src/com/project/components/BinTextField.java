package com.project.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 * Binary Input validator only allows 0 or 1
 */
public class BinTextField extends JTextField {
    private final int maxLength; // Maximum length allowed for the text field

    public static boolean isBinary(char c) {
        return c == '0' || c == '1';
    }

    public BinTextField(int maxColumns) {
        this.maxLength = maxColumns;
        this.setColumns(maxColumns);

        this.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c != KeyEvent.VK_BACK_SPACE &&  !isBinary(c) || getText().length() >= maxLength ) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
    }
}
