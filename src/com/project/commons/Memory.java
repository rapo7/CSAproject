package com.project.commons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.project.components.panels.ControlPanel;
import com.project.utils.HexParser;

import javax.swing.*;

/**
 * Uses Singleton pattern to implement memory as a hashmap
 */
public class Memory {
    private static Memory instance = null;
    private static Map<String, String> memoryMap;

    // Private constructor to prevent external instantiation
    private Memory() {
        memoryMap = new HashMap<>();
    }

    public static Memory getInstance() {
        if (instance == null) {
            instance = new Memory();
        }
        return instance;
    }

    // Methods to read and write to registerMap
    public String getValue(String address) {
        String address4 = (address.length() == 4) ? address : "0" + address;
        if (memoryMap.containsKey(address4)) {
            return memoryMap.get(address4);
        } else {
            System.err.println("Invalid memory access");
            ControlPanel.setHalt(true);
            return "0000";
        }
    }


    public void setValue(String address, String value) {
        memoryMap.put(address, value);
        System.out.println("Memory -- location" + address + "set to --" + value);
    }

    // load memory using the IPL.txt file from the current Directory
    public static boolean loadMemory(File file) {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                // Split the line into two parts using a space as the separator
                String[] parts = line.split(" ");

                if (parts.length == 2) {
                    String part1 = parts[0];
                    String part2 = parts[1];
                    memoryMap.put(part1, part2);
                } else {
                    System.err.println("Invalid line in IPL.txt: " + line);
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}



