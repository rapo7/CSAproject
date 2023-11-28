package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.utils.HexParser;
import com.project.components.panels.FlagPanel;

public class MLT {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(String rx, String ry) {
        String rxKey = regmap.getKey(rx);
        int rxint = Integer.parseInt(rx, 2);
        String ryKey = regmap.getKey(ry);
        int rxVal = Integer.parseInt(regmap.getValue(rxKey), 16);
        int ryVal = Integer.parseInt(regmap.getValue(ryKey), 16);
        String rxKeyPlusOne = regmap.getKey(String.format("%02d", Integer.parseInt(Integer.toBinaryString(rxint + 1))));

        if ((rx.equals("00") || rx.equals("10")) && (ry.equals("00") || ry.equals("10"))) {

            // Doing the multiplication
            long result = (long) rxVal * ryVal; // Use long to prevent overflow during multiplication

            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                // Overflow occurred
                FlagPanel.setOverflow(true);
            } else {
                // No overflow, you can use the result as is
                int lowerBits = getLowOrderBits((int) result);
                int higherBits = getHighOrderBits((int) result);

                regmap.setValue(rxKey, HexParser.inttoHexString(higherBits, 4));
                regmap.setValue(rxKeyPlusOne, HexParser.inttoHexString(lowerBits, 4));


            }
        }


    }

    private static int getLowOrderBits(int x) {
        return (x & 0xFFFF);
    }

    // getting the high 16 bits of an integer
    private static int getHighOrderBits(int x) {
        return x >> 16;
    }
}
