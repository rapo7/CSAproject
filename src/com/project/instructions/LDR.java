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
        String val =  memory.getValue(EA);
        switch (reg) {
            case "00":
                System.out.println("GPR0 setting");
                System.out.println(val);
                GPRPanel.setGpr0textField(val);
                break;
            case "01":
                GPRPanel.setGpr1textField(val);
                break;
            case "10":
                GPRPanel.setGpr2textField(val);
                break;
            case "11":
                regmap.setValue("GPR3", val);
                GPRPanel.setGpr3textField(val);
                break;

        }
    }
}
