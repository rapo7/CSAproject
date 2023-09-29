package com.project.instructions;

import com.project.commons.Memory;
import com.project.commons.RegisterMap;
import com.project.components.panels.IXRPanel;


public class LDX {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(Memory memory, String indReg, String EA) {
        String val = memory.getValue(EA);
        switch (indReg) {
            case "01":
                System.out.println("index 1");
                regmap.setValue("IXR1", val);
                IXRPanel.setIxr1textField(val);
                break;
            case "10":
                System.out.println("index 2");
                regmap.setValue("IXR2", val);
                IXRPanel.setIxr2textField(val);
                break;
            case "11":
                System.out.println("index 3");
                regmap.setValue("IXR3", val);
                IXRPanel.setIxr3textField(val);
                break;
            default:
                System.out.println("index 2");
                System.out.println("NOT A VALID value for IX");
                break;

        }
    }
}
