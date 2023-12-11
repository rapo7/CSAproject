package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.components.panels.RegisterPanel;
import com.project.utils.HexParser;

public class JSR {


    public static void execute(String EA) {
        int pcVal = Integer.parseInt(RegisterPanel.getPC(), 16);
        short newR3value = (short) (pcVal + 1);
        int intEA = Integer.parseInt(EA, 16);
        RegisterMap regmap = RegisterMap.getInstance();
        regmap.setValue("GPR3", newR3value);
        RegisterPanel.setPC(HexParser.inttoHexString(intEA));

    }
}
