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
        int rxVal = regmap.getValue(rxKey);
        int ryVal = regmap.getValue(ryKey);
        String rxKeyPlusOne = regmap.getKey(String.format("%02d", Integer.parseInt(Integer.toBinaryString(rxint + 1))));

        if ((rx.equals("00") || rx.equals("10")) && (ry.equals("00") || ry.equals("10"))) {

            // Doing the multiplication
            int result = rxVal * ryVal; // Use long to prevent overflow during multiplication

            if (result > Short.MAX_VALUE || result < Short.MIN_VALUE) {
                // Overflow occurred
                FlagPanel.setOverflow(true);
            } else {
                // No overflow, you can use the result as is
                int lowerBits = getLowOrderBits(result);
                int higherBits = getHighOrderBits(result);

                regmap.setValue(rxKey, (short) higherBits);
                regmap.setValue(rxKeyPlusOne, (short) lowerBits);


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
