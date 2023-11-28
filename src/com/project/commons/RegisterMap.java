package com.project.commons;

import com.project.components.panels.GPRPanel;

import java.util.HashMap;
import java.util.Map;

/**
 * This class has all the logic to maintain Registers
 * implements Singleton pattern
 * Single Source of Truth for Registers GPR and IXR
 */
public class RegisterMap {
    private static RegisterMap instance = null;
    private static Map<String, String> regMap;


    private static final String[] registers = {"GPR0", "GPR1", "GPR2", "GPR3", "IXR1", "IXR2", "IXR3"};

    // Private constructor to prevent external instantiation
    private  RegisterMap() {
        regMap = new HashMap<>();
        for(String x : registers){
            regMap.put(x, "0000");
        }
    }

    public static RegisterMap getInstance() {
        if (instance == null) {
            instance = new RegisterMap();
        }
        return instance;
    }

    // Methods to read and write to registerMap
    public  String getValue(String key) {
        return regMap.get(key);
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

    public  void setValue(String key, String value) {
        regMap.put(key, value);
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
                regMap.keySet()) {
            System.out.println(str + ":" + regMap.get(str));
        }
        System.out.println("End Printing Registers");
    }
}
