package com.project.instructions;

import com.project.components.panels.RegisterPanel;
import com.project.utils.HexParser;

public class JMA {
    public static void execute( String EA) {
        int intEA = Integer.parseInt(EA, 16);
            RegisterPanel.setPC(HexParser.inttoHexString(intEA));
    }
}
