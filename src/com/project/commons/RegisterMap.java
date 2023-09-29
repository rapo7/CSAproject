package com.project.commons;

import java.util.HashMap;
import java.util.Map;

public class RegisterMap {
    private static RegisterMap instance = null;
    private static Map<String, String> regMap;

    private static String[] registers = {"GPR0", "GPR1", "GPR2", "GPR3", "IXR1", "IXR2", "IXR3"};

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

    public  void setValue(String key, String value) {
        regMap.put(key, value);
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
