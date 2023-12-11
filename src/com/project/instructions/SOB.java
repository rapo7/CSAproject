package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.components.panels.RegisterPanel;
import com.project.utils.HexParser;

public class SOB {
    public static void execute(String r, String EA) {
        int intEA = Integer.parseInt(EA, 16);
        RegisterMap regmap = RegisterMap.getInstance();
        String regKey = regmap.getKey(r);
        short regVal = regmap.getValue(regKey);
        if (regVal - 1 > 0){
            regmap.setValue(regKey, (short) (regVal - 1));
            RegisterPanel.setPC(HexParser.inttoHexString(intEA, 3));
        }
    }
}
