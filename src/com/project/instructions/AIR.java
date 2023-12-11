package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.utils.HexParser;

public class AIR {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(String reg, String address) {
        String regKey = regmap.getKey(reg);
        short regVal = regmap.getValue(regKey);
        short result = (short) (regVal + HexParser.hexStringToInt(address));
        regmap.setValue(regKey, result);
    }
}