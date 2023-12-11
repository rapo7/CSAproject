package com.project.utils;

import com.project.commons.Memory;
import com.project.commons.RegisterMap;

/**
 * This class has getEA method
 * which calculates the Effective address based on IX , Indirect BIt and Address
 */
public class EffectiveAddress {
    private static RegisterMap regmap = RegisterMap.getInstance();
    private static Memory memory  = Memory.getInstance();

    public static String getEA(String Ibit, String IX, String address) {
        String hexAddress = HexParser.inttoHexString(Integer.parseInt(address, 2));
        System.out.println(IX);
        System.out.println();
        String EA = hexAddress;
        if (Ibit.equals("0")) {
            switch (IX) {
                case "00" -> {
                    return EA;
                }
                case "01" -> {

                    EA = HexParser.inttoHexString(regmap.getValue("IXR1") + Integer.parseInt(address, 2));
                }
                case "10" -> {
                    EA = HexParser.inttoHexString(regmap.getValue("IXR2") + Integer.parseInt(address, 2));
                }
                case "11" -> {
                    EA = HexParser.inttoHexString(regmap.getValue("IXR3") + Integer.parseInt(address, 2));
                }
                default -> System.out.println("INVALID IXR");
            }

        } else {
            switch (IX) {
                case "00" -> {
                    return memory.getValue(hexAddress);
                }
                case "01" -> {
                    EA = memory.getValue(HexParser.inttoHexString(regmap.getValue("IXR1") + Integer.parseInt(address, 2)));
                }
                case "10" -> {
                    EA = memory.getValue(HexParser.inttoHexString(regmap.getValue("IXR2") + Integer.parseInt(address, 2)));
                }
                case "11" -> {
                    EA = memory.getValue(HexParser.inttoHexString(regmap.getValue("IXR3") + Integer.parseInt(address, 2)));
                }
            }
        }

        return EA;
    }
}
