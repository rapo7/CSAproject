package com.project.utils;

/**
 * This class has the utilities to convert Hex to binary and vice versa
 */
public class HexParser {
    public static int hexStringToInt(String hexString) {
        // Parse the hexadecimal string to an integer
        return Integer.parseInt(hexString, 16);

    }


    public static String inttoHexString(int x) {
        String input = Integer.toHexString(x).toUpperCase(); // The input string
        int desiredLength = 4; // The desired length of the padded string
        // Use String.format to left-pad with zeros
        return String.format("%" + desiredLength + "s", input).replace(' ', '0');
    }

    public static String inttoHexString(int x, int desiredLength) {
        String input = Integer.toHexString(x).toUpperCase(); // The input string
        // The desired length of the padded string
        // Use String.format to left-pad with zeros
        return String.format("%" + desiredLength + "s", input).replace(' ', '0');
    }

    public static boolean isAllowedHex(char c) {
        char[] charArray = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f'};
        for (char chr :
                charArray) {
            if (chr == c) {
                return true;
            }
        }
        return false;
    }

    public static String binaryToHex(String binary, int desiredLength) {
        int decimalValue = Integer.parseInt(binary, 2);
        String input = Integer.toHexString(decimalValue).toUpperCase();
        return String.format("%" + desiredLength + "s", input).replace(' ', '0');
    }

    public static String hexToBinary(String hex) {
        int decimalValue = Integer.parseInt(hex, 16);
        return Integer.toBinaryString(decimalValue);
    }

    public static String intstrtoBin(String x, int desiredLength) {
        String input = Integer.toBinaryString(Integer.parseInt(x));
        return String.format("%" + desiredLength + "s", input).replace(' ', '0');
    }
    public static String inttoBin(int x, int desiredLength) {
        String input = Integer.toBinaryString(x);
        return String.format("%" + desiredLength + "s", input).replace(' ', '0');
    }


}
