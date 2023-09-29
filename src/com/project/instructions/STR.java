package com.project.instructions;

import com.project.commons.Memory;
import com.project.commons.RegisterMap;

public class STR {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(Memory memory, String reg, String EA) {
        switch (reg) {
            case "00" -> {
                System.out.println("GPR0 getting");
                String regVal = regmap.getValue("GPR0");
                memory.setValue(EA, regVal);
            }
            case "01" -> {
                System.out.println("GPR1 getting");
                String regVal = regmap.getValue("GPR1");
                memory.setValue(EA, regVal);
            }
            case "10" -> {
                System.out.println("GPR2 getting");
                String regVal = regmap.getValue("GPR2");
                memory.setValue(EA, regVal);
            }
            case "11" -> {
                System.out.println("GPR3 getting");
                String regVal = regmap.getValue("GPR3");
                memory.setValue(EA, regVal);
            }
            default -> {
                System.out.println("Invalid value for GPR");
            }

        }
    }
}
