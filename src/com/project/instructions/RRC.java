package com.project.instructions;


import com.project.commons.RegisterMap;
import com.project.utils.HexParser;

public class RRC {
    public static void execute(String reg, int lorr, int aorl, int count) {
        RegisterMap registerMap = RegisterMap.getInstance();
        String regKey = registerMap.getKey(reg);
        int regVal = registerMap.getValue(regKey);
        String binStr = HexParser.inttoBin(regVal, 16);
        String result;
        if (count != 0) {
            if (aorl != 0) {
                if (lorr != 0) {
                    result = arithmeticRotateLeft(binStr, count);
                    registerMap.setValue(regKey, Short.parseShort(result, 2));
                } else {
                    result = arithmeticRotateRight(binStr, count);
                    registerMap.setValue(regKey, Short.parseShort(result, 2));
                }
            } else {
                if (lorr != 0) {
                    result = logicalRotateLeft(binStr, count);
                    registerMap.setValue(regKey, Short.parseShort(result, 2));
                } else {
                    result = logicalRotateRight(binStr, count);
                    registerMap.setValue(regKey, Short.parseShort(result, 2));
                }
            }
        }
    }

    public static String logicalRotateLeft(String binaryString, int count) {
        int length = binaryString.length();
        count %= length; // To handle cases where count is greater than the length

        // Perform logical left rotation
        return binaryString.substring(count) + binaryString.substring(0, count);
    }

    public static String arithmeticRotateLeft(String binaryString, int count) {
        int length = binaryString.length();
        count %= length; // To handle cases where count is greater than the length

        // Perform arithmetic left rotation
        return binaryString.substring(count) + binaryString.substring(0, count);
    }

    public static String logicalRotateRight(String binaryString, int count) {
        int length = binaryString.length();
        count %= length; // To handle cases where count is greater than the length

        // Perform logical right rotation
        return binaryString.substring(length - count) + binaryString.substring(0, length - count);
    }

    public static String arithmeticRotateRight(String binaryString, int count) {
        int length = binaryString.length();
        count %= length; // To handle cases where count is greater than the length

        // Perform arithmetic right rotation
        char signBit = binaryString.charAt(0);
        return String.valueOf(signBit).repeat(count) + binaryString.substring(0, length - count);
    }
}


