package com.project.instructions;

import com.project.commons.RegisterMap;

public class ORR {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(String rx, String ry) {
        String rxKey = regmap.getKey(rx);
        String ryKey = regmap.getKey(ry);
        String rxVal = regmap.getValue(rxKey);
        String ryVal = regmap.getValue(ryKey);
        String result = bitwiseOr(rxVal, ryVal);
        regmap.setValue(rxKey, result);
    }

    public static String bitwiseOr(String hexString1, String hexString2) {
        if (hexString1.length() != hexString2.length()) {
            throw new IllegalArgumentException("Hex strings must have the same length");
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < hexString1.length(); i++) {
            char hexChar1 = hexString1.charAt(i);
            char hexChar2 = hexString2.charAt(i);

            // Convert hex characters to integers
            int intValue1 = Character.digit(hexChar1, 16);
            int intValue2 = Character.digit(hexChar2, 16);

            // Perform bitwise AND operation
            int andResult = intValue1 | intValue2;

            // Convert the result back to hex character
            char hexResultChar = Character.forDigit(andResult, 16);

            result.append(hexResultChar);
        }

        return result.toString();
    }

}
