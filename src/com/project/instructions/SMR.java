package com.project.instructions;

import com.project.commons.Memory;
import com.project.commons.RegisterMap;
import com.project.utils.HexParser;

public class SMR {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(Memory memory, String reg, String EA) {
        String regKey = regmap.getKey(reg);
        short regVal = regmap.getValue(regKey);
        short result = (short) (regVal - HexParser.hexStringToInt(memory.getValue(EA)));
        regmap.setValue(regKey, result);
    }
}
