package com.project.components;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.regex.Pattern;

public class HexTextField extends JTextField {
    private static final Pattern hexPattern = Pattern.compile("[0-9A-Fa-f]+");
    private int maxCols;

    public HexTextField(int columns) {
        super(columns);
        super.setColumns(columns);
        maxCols = columns;
        setDocument(new HexDocument());
    }

    private class HexDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }

            String currentText = getText(0, getLength());
            String beforeOffset = currentText.substring(0, offs);
            String afterOffset = currentText.substring(offs);

            String proposedResult = beforeOffset + str + afterOffset;

            if (isValidHex(proposedResult) && proposedResult.length() <= maxCols) { // Use maxCols instead of max_cols
                super.insertString(offs, str, a);
            } else {
                Toolkit.getDefaultToolkit().beep(); // Beep if invalid input is provided.
            }
        }
    }

    private boolean isValidHex(String input) {
        return hexPattern.matcher(input).matches();
    }

    @Override
    public void setText(String t) {
        if (isValidHex(t) && t.length() <= maxCols) {
            super.setText(t);
        } else {
            //beep
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
