package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.utils.HexParser;
import com.project.components.panels.FlagPanel;

public class TRR {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(String rx, String ry) {
        String rxKey = regmap.getKey(rx);
        String ryKey = regmap.getKey(ry);
        String rxVal = regmap.getValue(rxKey);
        String ryVal = regmap.getValue(ryKey);

        if (rxVal.equals(ryVal)) {
                FlagPanel.setDivZero(true);
        }
    }

}
