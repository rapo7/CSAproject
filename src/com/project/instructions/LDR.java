package com.project.instructions;

import com.project.commons.Memory;
import com.project.commons.RegisterMap;
import com.project.components.panels.GPRPanel;


public class LDR {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(Memory memory, String reg, String EA) {
        String val =  memory.getValue(EA);
        switch (reg) {
            case "00":
                System.out.println("GPR0 setting");
                System.out.println(val);
                regmap.setValue("GPR0", val);
                GPRPanel.setGpr0textField(val);
                break;
            case "01":
                regmap.setValue("GPR1", val);
                GPRPanel.setGpr1textField(val);
                break;
            case "10":
                regmap.setValue("GPR2", val);
                GPRPanel.setGpr2textField(val);
                break;
            case "11":
                regmap.setValue("GPR3", val);
                GPRPanel.setGpr3textField(val);
                break;

        }
    }
}
