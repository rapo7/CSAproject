package com.project.instructions;

import com.project.commons.RegisterMap;

import com.project.components.panels.ControlPanel;
import com.project.components.panels.IOPanel;
import com.project.utils.HexParser;

import javax.naming.ldap.Control;
import javax.swing.*;

public class OUT {
    public static void execute(String reg, short devid) {
        RegisterMap regmap = RegisterMap.getInstance();
        String regKey = regmap.getKey(reg);
        ControlPanel.setHalt(true);
        if (devid == 1) {
            IOPanel.setOutput(HexParser.inttoHexString(regmap.getValue(regKey), 4));
        }
        ControlPanel.setHalt(false);
    }
}
