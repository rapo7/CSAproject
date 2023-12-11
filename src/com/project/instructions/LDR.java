package com.project.instructions;

import com.project.commons.Memory;
import com.project.commons.RegisterMap;
import com.project.components.panels.GPRPanel;


/**
 * The Load register from memory
 */
public class LDR {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(Memory memory, String reg, String EA) {
        String val = memory.getValue(EA);
        String regKey = regmap.getKey(reg);
        regmap.setValue(regKey, Short.parseShort(val, 16));

    }
}
