package com.project.instructions;
import com.project.commons.RegisterMap;

public class NOT {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(String rx ) {
        String rxKey = regmap.getKey(rx);
        String rxVal = regmap.getValue(rxKey);
        String negrxVal = negateHexString(rxVal);

        regmap.setValue(rxKey, negrxVal);

    }
    private static String negateHexString(String hexString) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < hexString.length(); i++) {
            char hexChar = hexString.charAt(i);

            // Perform bitwise NOT operation on each hex character
            int intValue = Character.digit(hexChar, 16);
            int negatedValue = 15 - intValue; // Bitwise NOT of a hexadecimal digit x is 15-x

            // Convert the negated value back to hex character
            char negatedHexChar = Character.forDigit(negatedValue, 16);

            result.append(negatedHexChar);
        }

        return result.toString();
    }


}
