package com.project.instructions;

import com.project.commons.RegisterMap;

public class AND {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(String rx, String ry) {
        String rxKey = regmap.getKey(rx);
        String ryKey = regmap.getKey(ry);
        short rxVal = regmap.getValue(rxKey);
        short ryVal = regmap.getValue(ryKey);
        short result = (short) (rxVal & ryVal);
        regmap.setValue(rxKey, result);
    }

}
