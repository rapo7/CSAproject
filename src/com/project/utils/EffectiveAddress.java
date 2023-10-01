package com.project.utils;

import com.project.commons.Memory;
import com.project.commons.RegisterMap;

/**
 * This class has getEA method
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
                    String ixr1 = regmap.getValue("IXR1");
                    EA = HexParser.inttoHexString(HexParser.hexStringToInt(ixr1) + Integer.parseInt(address, 2));
                }
                case "10" -> {
                    String ixr2 = regmap.getValue("IXR2");
                    EA = HexParser.inttoHexString(HexParser.hexStringToInt(ixr2) + Integer.parseInt(address, 2));
                }
                case "11" -> {
                    String ixr3 = regmap.getValue("IXR3");
                    EA = HexParser.inttoHexString(HexParser.hexStringToInt(ixr3) + Integer.parseInt(address, 2));
                }
                default -> System.out.println("INVALID IXR");
            }

        } else {
            switch (IX) {
                case "00" -> {
                    return memory.getValue(hexAddress);
                }
                case "01" -> {
                    String ixr1 = regmap.getValue("IXR1");
                    EA = memory.getValue(HexParser.inttoHexString(HexParser.hexStringToInt(ixr1) + Integer.parseInt(address, 2)));
                }
                case "10" -> {
                    String ixr2 = regmap.getValue("IXR2");
                    EA = memory.getValue(HexParser.inttoHexString(HexParser.hexStringToInt(ixr2) + Integer.parseInt(address, 2)));
                }
                case "11" -> {
                    String ixr3 = regmap.getValue("IXR3");
                    EA = memory.getValue(HexParser.inttoHexString(HexParser.hexStringToInt(ixr3) + Integer.parseInt(address, 2)));
                }
            }
        }

        return EA;
    }
}
