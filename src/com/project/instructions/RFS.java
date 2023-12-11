package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.components.panels.RegisterPanel;
import com.project.utils.HexParser;

public class RFS {
    public static void execute(String Immed) {
        int intImmed = Integer.parseInt(Immed, 2);
        String newR3value = HexParser.inttoHexString(intImmed, 4);
        String newPCvalue = HexParser.inttoHexString(intImmed, 3);
        RegisterMap regmap = RegisterMap.getInstance();
        regmap.setValue("GPR3", Short.parseShort(newR3value));
        RegisterPanel.setPC(newPCvalue);

    }
}
