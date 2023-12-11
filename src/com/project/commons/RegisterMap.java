package com.project.commons;

import com.project.components.panels.GPRPanel;
import com.project.utils.HexParser;

import java.util.HashMap;
import java.util.Map;

/**
 * This class has all the logic to maintain Registers
 * implements Singleton pattern
 * Single Source of Truth for Registers GPR and IXR
 */
public class RegisterMap {
    private static RegisterMap instance = null;

    private static Map<String, Short> regShortMap;


    private static final String[] registers = {"GPR0", "GPR1", "GPR2", "GPR3", "IXR1", "IXR2", "IXR3"};

    // Private constructor to prevent external instantiation
    private  RegisterMap() {
        regShortMap = new HashMap<>();
        for(String x : registers){
            this.setValue(x, (short) 0);
        }
    }

    public static RegisterMap getInstance() {
        if (instance == null) {
            instance = new RegisterMap();
        }
        return instance;
    }

    // Methods to read and write to registerMap
    public  short getValue(String key) {
        return regShortMap.get(key);
    }

    public String getKey(String regNum) {
        switch (regNum) {
            case "00" -> {
                return "GPR0";
            }
            case "01" -> {
                return "GPR1";
            }
            case "10" -> {
                return "GPR2";
            }
            case "11" -> {
                return "GPR3";
            }
        }
        System.out.println("Invalid Register value");
        return "00";
    }

    public  void setValue(String key, short value) {
        String strVal = HexParser.inttoHexString(value, 4);
        regShortMap.put(key, value);
        switch (key) {
            case "GPR0" -> {
                GPRPanel.setGpr0textField(strVal);
            }
            case "GPR1" -> {
                GPRPanel.setGpr1textField(strVal);
            }
            case "GPR2" -> {
                GPRPanel.setGpr2textField(strVal);
            }
            case "GPR3" -> {
                GPRPanel.setGpr3textField(strVal);
            }
        }
        printRegisters();
    }
    public  void setHardValue(String key, String value) {
        regShortMap.put(key, Short.parseShort(value, 16));
        switch (key) {
            case "GPR0" -> {
                GPRPanel.setGpr0textField(value);
            }
            case "GPR1" -> {
                GPRPanel.setGpr1textField(value);
            }
            case "GPR2" -> {
                GPRPanel.setGpr2textField(value);
            }
            case "GPR3" -> {
                GPRPanel.setGpr3textField(value);
            }
        }
        printRegisters();
    }
    public  void printRegisters(){
        System.out.println("started printing Registers");
        for (String str :
                regShortMap.keySet()) {
            System.out.println(str + ":" + regShortMap.get(str));
        }
        System.out.println("End Printing Registers");
    }
}
