package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.components.panels.RegisterPanel;
import com.project.utils.HexParser;

public class JCC {
    public static void execute( String reg, String EA) {
        RegisterMap regmap = RegisterMap.getInstance();
        int intEA = Integer.parseInt(EA, 16);
        //TODO
        if (reg.equals("00")) {

            RegisterPanel.setPC(HexParser.inttoHexString(intEA));
        } else {
            RegisterPanel.incrementPC();
        }
    }
}
