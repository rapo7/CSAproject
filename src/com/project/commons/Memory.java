package com.project.commons;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.project.utils.HexParser;

/**
 * Uses Singleton pattern to implement memory as a hashmap
 */
public class Memory {
    private static Memory instance = null;
    private static Map<String, String> memoryMap;

    // Private constructor to prevent external instantiation
    private Memory() {
        memoryMap = new HashMap<>();
        for (int i = 0; i < Constants.MEMORY_BOUND; i++) {
            memoryMap.put(HexParser.inttoHexString(i), "0000");
        }
    }

    public static Memory getInstance() {
        if (instance == null) {
            instance = new Memory();
        }
        return instance;
    }

    // Methods to read and write to registerMap
    public String getValue(String address) {
        if (address.length() == 4)
            return memoryMap.get(address);
        else
            return memoryMap.get("0" + address);

    }

    public void setValue(String address, String value) {
        memoryMap.put(address, value);
        System.out.println("Memory -- location" + address + "set to --" + value);
    }

    // load memory using the IPL.txt file from the current Directory
    public static boolean loadMemory() {

        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory + "/IPL.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()))) {
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
            System.out.println("IPL File not found");
            return false;
        }

    }
}



