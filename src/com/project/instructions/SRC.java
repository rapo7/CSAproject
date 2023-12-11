package com.project.instructions;

import com.project.commons.RegisterMap;
import com.project.utils.HexParser;

public class SRC {
    public static void execute(String reg, int lorr, int aorl, int count) {
        RegisterMap registerMap = RegisterMap.getInstance();
        String regKey = registerMap.getKey(reg);
        int regVal = registerMap.getValue(regKey);
        String binStr = HexParser.inttoBin(regVal, 16);
        String result;
        if (count != 0) {
            if (aorl != 0) {
                if (lorr != 0) {
                    result = arithmeticShiftLeft(binStr, count);
                    registerMap.setValue(regKey, Short.parseShort(result, 2));
                } else {
                    result = arithmeticShiftRight(binStr, count);
                    registerMap.setValue(regKey, Short.parseShort(result, 2));
                }
            } else {
                if (lorr != 0) {
                    result = logicalShiftLeft(binStr, count);
                    registerMap.setValue(regKey, Short.parseShort(result, 2));
                } else {
                    result = logicalShiftRight(binStr, count);
                    registerMap.setValue(regKey, Short.parseShort(result, 2));
                }
            }
        }
    }

    public static String logicalShiftLeft(String binaryString, int count) {
        int length = binaryString.length();
        count %= length; // To handle cases where count is greater than the length

        // Perform logical left shift operation
        return binaryString.substring(count) + "0".repeat(count);
    }

    public static String arithmeticShiftLeft(String binaryString, int count) {
        int length = binaryString.length();
        count %= length; // To handle cases where count is greater than the length

        // Perform arithmetic left shift operation
        return binaryString.substring(count) + "0".repeat(count);
    }

    public static String logicalShiftRight(String binaryString, int count) {
        int length = binaryString.length();
        count %= length; // To handle cases where count is greater than the length

        // Perform logical right shift operation
        return "0".repeat(count) + binaryString.substring(0, length - count);
    }

    public static String arithmeticShiftRight(String binaryString, int count) {
        int length = binaryString.length();
        count %= length; // To handle cases where count is greater than the length

        // Perform arithmetic right shift operation
        char signBit = binaryString.charAt(0);
        return String.valueOf(signBit).repeat(count) + binaryString.substring(0, length - count);
    }
}
