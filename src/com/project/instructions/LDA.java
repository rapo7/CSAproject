package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.components.panels.GPRPanel;


/**
 * The logic for load register with address
 */
public class LDA {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(String reg, String EA) {
        String regKey = regmap.getKey(reg);
        regmap.setValue(regKey, Short.parseShort(EA, 16));
    }
}
