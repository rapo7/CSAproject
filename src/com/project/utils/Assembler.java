package com.project.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Assembler {

    static Map<String, String> opcodeMap = new HashMap<>();
    static Map<String, String> resMap = new HashMap<>();
    static Map<String, String> symbolMap = new HashMap<>();
    static int addressCounter = 0;

    static Set<String> arithmeticOps = new HashSet<>();
    static Set<String> shiftRotateOps = new HashSet<>();
    static Set<String> ioOps = new HashSet<>();

    static Set<String> lenOneOps = new HashSet<>();


    static {
        arithmeticOps.add("MLT");
        arithmeticOps.add("DVD");
        arithmeticOps.add("TRR");
        arithmeticOps.add("AND");
        arithmeticOps.add("ORR");
        shiftRotateOps.add("SRC");
        shiftRotateOps.add("RRC");
        ioOps.add("IN");
        ioOps.add("OUT");
        ioOps.add("CHK");
        lenOneOps.add("HLT");
        lenOneOps.add("NOT");
        lenOneOps.add("RFS");

    }


    static {
        opcodeMap.put("TRAP", "011000");
        opcodeMap.put("LDR", "000001");
        opcodeMap.put("STR", "000010");
        opcodeMap.put("LDA", "000011");
        opcodeMap.put("LDX", "100001");
        opcodeMap.put("STX", "100010");
        opcodeMap.put("JZ", "001000");
        opcodeMap.put("JNE", "001001");
        opcodeMap.put("JCC", "001010");
        opcodeMap.put("JMA", "001011");
        opcodeMap.put("JSR", "001100");
        opcodeMap.put("RFS", "001101");
        opcodeMap.put("JGE", "001111");
        opcodeMap.put("AMR", "000100");
        opcodeMap.put("SMR", "000101");
        opcodeMap.put("AIR", "000110");
        opcodeMap.put("SIR", "000111");
        opcodeMap.put("MLT", "010000");
        opcodeMap.put("DVD", "010001");
        opcodeMap.put("TRR", "010010");
        opcodeMap.put("AND", "010011");
        opcodeMap.put("ORR", "010100");
        opcodeMap.put("NOT", "010101");
        opcodeMap.put("SRC", "011111");
        opcodeMap.put("RRC", "100000");
        opcodeMap.put("IN", "110101");
        opcodeMap.put("OUT", "110110");
        opcodeMap.put("CHK", "110111");
        opcodeMap.put("FADD", "100001");
        opcodeMap.put("FSUB", "100010");
        opcodeMap.put("VADD", "100011");
        opcodeMap.put("VSUB", "100100");
        opcodeMap.put("CNVRT", "100101");
        opcodeMap.put("LDFR", "101000");
        opcodeMap.put("STFR", "101001");
    }


    static String csParse(String opcode, String[] csv) {
        if (csv.length == 1) {
            if (opcode.equals("NOT"))
                return HexParser.intstrtoBin(csv[0], 2) + "00000000";
            else if (opcode.equals("RFS"))
                return "00000" + HexParser.intstrtoBin(csv[0], 5);

        } else if (csv.length == 2) {
            if (ioOps.contains(opcode))
                return HexParser.intstrtoBin(csv[0], 2) + "00" + "0" + HexParser.intstrtoBin(csv[1], 5);
            else if (arithmeticOps.contains(opcode))
                return HexParser.intstrtoBin(csv[0], 2) + HexParser.intstrtoBin(csv[1], 2) + "000000";
            return "00" + HexParser.intstrtoBin(csv[0], 2) + "0" + HexParser.intstrtoBin(csv[1], 5);
        } else if (csv.length == 3) {
            return HexParser.intstrtoBin(csv[0], 2) + HexParser.intstrtoBin(csv[1], 2) + "0" + HexParser.intstrtoBin(csv[2], 5);
        } else if (csv.length == 4) {
            if (shiftRotateOps.contains(opcode))
                return HexParser.intstrtoBin(csv[0], 2) + HexParser.intstrtoBin(csv[3], 2) + HexParser.intstrtoBin(csv[2], 2) + "00" + HexParser.intstrtoBin(csv[1], 5);
            return HexParser.intstrtoBin(csv[0], 2) + HexParser.intstrtoBin(csv[1], 2) + "1" + HexParser.intstrtoBin(csv[2], 5);
        }
        return null;
    }

    //

    /**
     * This will take the instruction with Label and handles the label and  passes instruction to Instruction-handler
     *
     * @param parts
     */
    private static void handleLabelDefinition(String[] parts) {
        String label = parts[0].substring(0, parts[0].length() - 1);
        symbolMap.put(label, HexParser.inttoHexString(addressCounter, 4));
        if (parts.length >= 1) {
            String[] partsnew = Arrays.copyOfRange(parts, 1, parts.length);
            handleInstruction(partsnew);
        } else {
            System.out.println("Invalid Instruction file");
        }
    }

    /**
     * This function takes parts of the instruction as arguments an d parses the instruction based on the length of the parts array
     *
     * @param parts
     */
    private static void handleInstruction(String[] parts) {
        String opcode = parts[0].toUpperCase();

        if (opcodeMap.containsKey(opcode)) {
            String binVal = opcodeMap.get(opcode) + csParse(opcode, parts[1].split(","));
            String hexVal = HexParser.binaryToHex(binVal, 4);
            resMap.put(HexParser.inttoHexString(addressCounter, 4), hexVal);
            addressCounter++;
        } else if (parts[0].equalsIgnoreCase("HLT")) {
            System.out.println(addressCounter);
            resMap.put(HexParser.inttoHexString(addressCounter, 4), "0000");
        } else if (parts[0].equalsIgnoreCase("LOC")) {
            addressCounter = Integer.parseInt(parts[1]);
        } else if (parts[0].equalsIgnoreCase("Data")) {
            handleData(parts);
        } else {
            System.out.println("Invalid Input file");
        }
    }

    /**
     * This takes the parts array and handles the DATA instructions
     *
     * @param parts
     */
    private static void handleData(String[] parts) {
        int data;
        try {
            data = Integer.parseInt(parts[1]);
            resMap.put(HexParser.inttoHexString(addressCounter, 4), HexParser.inttoHexString(data, 4));
        } catch (NumberFormatException e) {
            resMap.put(parts[1], HexParser.inttoHexString(addressCounter, 4));
        } finally {
            addressCounter++;
        }
    }

    public static void assembleFromFile(File file) throws IOException {


        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                //ignore comments (lines that that start with a ';'
                if (line.startsWith(";")) {
                    continue;
                }

                String[] parts = line.split(" ");
                if (parts.length < 2) {
                    continue; // Skip lines with less than 2 parts
                }

                if (parts[0].contains(":")) {
                    handleLabelDefinition(parts);
                } else {
                    handleInstruction(parts);
                }
            }

            for (Map.Entry<String, String> me : symbolMap.entrySet()) {
                if (resMap.containsKey(me.getKey())) {
                    String address = resMap.get(me.getKey());
                    resMap.remove(me.getKey());
                    resMap.put(address, me.getValue());
                } else {
                    System.out.println("Cannot Resolve Symbol" + me.getKey());
                }
            }
            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String fileName = "output_" + timestamp + ".txt";
            String filePath = "./" + fileName;  // "./" specifies the current directory.

            BufferedWriter writer = null;
            try {
                // Create a BufferedWriter to write to the file.
                writer = new BufferedWriter(new FileWriter(filePath));

                List<String> keysList = new ArrayList<>(resMap.keySet());
                Collections.sort(keysList);

                for (String key : keysList) {
                    String newline = key + " " + resMap.get(key);
                    writer.write(newline);
                    writer.newLine();
                }

                System.out.println("Lines have been written to " + filePath);
            } catch (IOException e) {
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            } finally {
                assert writer != null;
                writer.close();
            }
        }
    }
}