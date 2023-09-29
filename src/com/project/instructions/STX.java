package com.project.instructions;

import com.project.commons.Memory;
import com.project.commons.RegisterMap;

public class STX {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(Memory memory, String indReg, String EA) {
        switch (indReg) {
            case "01" -> {
                System.out.println("GPR1 getting");
                String regVal = regmap.getValue("IXR1");
                memory.setValue(EA, regVal);
            }
            case "10" -> {
                System.out.println("IXR2 getting");
                String regVal = regmap.getValue("IXR2");
                memory.setValue(EA, regVal);
            }
            case "11" -> {
                System.out.println("IXR3 getting");
                String regVal = regmap.getValue("IXR3");
                memory.setValue(EA, regVal);
            }
            default -> System.out.println("Invalid value for IXR");

        }
    }
}
