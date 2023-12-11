package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.components.panels.RegisterPanel;
import com.project.utils.HexParser;

public class JZ {
    public static void execute( String reg, String EA) {
        int intEA = Integer.parseInt(EA, 16);
        RegisterMap regmap = RegisterMap.getInstance();
        short regContent = regmap.getValue(regmap.getKey(reg));
        if (regContent == 0) {
            RegisterPanel.setPC(HexParser.inttoHexString(intEA));
        } else {
            RegisterPanel.incrementPC();
        }
    }
}
