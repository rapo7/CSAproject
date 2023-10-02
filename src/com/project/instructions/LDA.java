package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.components.panels.GPRPanel;


/**
 * The logic for load register with address
 */
public class LDA {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(String reg, String EA) {
        switch (reg) {
            case "00":
                System.out.println("GPR0 setting");
                regmap.setValue("GPR0", EA);
                GPRPanel.setGpr0textField(EA);
                break;
            case "01":
                regmap.setValue("GPR1", EA);
                GPRPanel.setGpr1textField(EA);
                break;
            case "10":
                regmap.setValue("GPR2", EA);
                GPRPanel.setGpr2textField(EA);
                break;
            case "11":
                regmap.setValue("GPR3", EA);
                GPRPanel.setGpr3textField(EA);
                break;

        }
    }
}
