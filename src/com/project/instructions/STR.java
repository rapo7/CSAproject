package com.project.instructions;

import com.project.commons.Memory;
import com.project.commons.RegisterMap;
import com.project.utils.HexParser;

/**
 * Store the register to memory
 */
public class STR {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(Memory memory, String reg, String EA) {
        String regKey = regmap.getKey(reg);
        short regVal = regmap.getValue(regKey);
        memory.setValue(EA, HexParser.inttoHexString(regVal));
    }
}
