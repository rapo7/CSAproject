package com.project.instructions;

import com.project.commons.Memory;
import com.project.commons.RegisterMap;
import com.project.utils.HexParser;

/**
 * Store the Index Register to memory
 */
public class STX {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(Memory memory, String indReg, String EA) {
        switch (indReg) {
            case "01" -> {
                System.out.println("GPR1 getting");
                short regVal = regmap.getValue("IXR1");
                memory.setValue(EA, HexParser.inttoHexString(regVal, 4));
            }
            case "10" -> {
                System.out.println("IXR2 getting");
                short regVal = regmap.getValue("IXR2");
                memory.setValue(EA, HexParser.inttoHexString(regVal, 4));
            }
            case "11" -> {
                System.out.println("IXR3 getting");
                short regVal = regmap.getValue("IXR3");
                memory.setValue(EA, HexParser.inttoHexString(regVal, 4));
            }
            default -> System.out.println("Invalid value for IXR");

        }
    }
}
