package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.utils.HexParser;
import com.project.components.panels.FlagPanel;

public class DVD {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(String rx, String ry) {
        String rxKey = regmap.getKey(rx);
        int rxint = Integer.parseInt(rx, 2);
        String ryKey = regmap.getKey(ry);
        int rxVal = Integer.parseInt(regmap.getValue(rxKey), 16);
        int ryVal = Integer.parseInt(regmap.getValue(ryKey), 16);
        String rxKeyPlusOne = regmap.getKey(String.format("%02d", Integer.parseInt(Integer.toBinaryString(rxint + 1))));

        if ((rx.equals("00") || rx.equals("10")) && (ry.equals("00") || ry.equals("10"))) {

            if(ryVal == 0){
                FlagPanel.setDivZero(true);
                return;
            }
            // Doing the multiplication
            int quo = rxVal / ryVal;
            int rem = rxVal % ryVal;

            regmap.setValue(rxKey, HexParser.inttoHexString(quo, 4));
            regmap.setValue(rxKeyPlusOne, HexParser.inttoHexString(rem, 4));
        }


    }

}
