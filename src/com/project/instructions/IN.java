package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.components.InputDialog;
import com.project.components.panels.ControlPanel;

import javax.swing.*;

public class IN {
    public static void execute(String reg, short devid) {
        RegisterMap regmap = RegisterMap.getInstance();
        String regKey = regmap.getKey(reg);
        ControlPanel.setHalt(true);
        if (devid == 0) {
            JDialog jd = InputDialog.createCustomDialog();
            jd.setVisible(true);
            regmap.setValue(regKey, Short.parseShort(InputDialog.getUserInput(),16));
        }
        ControlPanel.setHalt(true);
    }
}
