package com.project.instructions;

import com.project.commons.Memory;
import com.project.components.panels.FlagPanel;
import com.project.utils.EffectiveAddress;

import java.util.HashMap;
import java.util.Map;

/**
 * This class has all the code to parse and execute instruction base don opcode
 */
public class InstructionParser {
    static Memory memory = Memory.getInstance();

    static Map<String, String> opcodeMap = new HashMap<>();
    static Map<String, String> arithmeticOps = new HashMap<>();
    static Map<String, String> shiftRotateOps = new HashMap<>();
    static Map<String, String> ioOps = new HashMap<>();
    static Map<String, String> lenOneOps = new HashMap<>();


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
    }

    static {
        arithmeticOps.put("010000", "MLT");
        arithmeticOps.put("010001", "DVD");
        arithmeticOps.put("010010", "TRR");
        arithmeticOps.put("010011", "AND");
        arithmeticOps.put("010100", "ORR");
        arithmeticOps.put("010101", "NOT");

        shiftRotateOps.put("011111", "SRC");
        shiftRotateOps.put("100000", "RRC");

        ioOps.put("110101", "IN");
        ioOps.put("110110", "OUT");
        ioOps.put("110111", "CHK");

        lenOneOps.put("000000", "HLT");
        lenOneOps.put("001101", "RFS");

    }

    public static String getInstructionFromHex(String hexIns) {
        String bin = Integer.toBinaryString(Integer.parseInt(hexIns, 16));
        if (bin.length() != 16) {
            bin = String.format("%" + 16 + "s", bin).replace(' ', '0');
        }
        System.out.println(bin);
        return bin;
    }

    public static void fromIR(String irText) {



        String localopcodestr, localreg, localindReg, localindirect, localaddress;

        String ins = getInstructionFromHex(irText);
        localopcodestr = ins.substring(0, 6);
        localreg = ins.substring(6, 8);
        localindReg = ins.substring(8, 10);
        localindirect = ins.substring(10, 11);
        localaddress = ins.substring(11, 16);

        if (shiftRotateOps.containsKey(localopcodestr)) {
            executeShift(ins, localopcodestr);
        } else if (ioOps.containsKey(localopcodestr)) {
            executeio(ins, localopcodestr);
        } else if (arithmeticOps.containsKey(localopcodestr)) {
            FlagPanel.clearFlags();
            executeArthmetic(localopcodestr, localreg, localindReg);
        } else if (lenOneOps.containsKey(localopcodestr)) {
            executelenOne(ins, localopcodestr);
        } else {
            executeNormal(localopcodestr, localreg, localindReg, localindirect, localaddress);
        }
    }

    private static void executeArthmetic(String localopcodestr, String rx, String ry) {
        String opcodeCode = arithmeticOps.get(localopcodestr);
        switch (opcodeCode) {
            case "MLT" -> {
                System.out.println("in parser MLT started");
                MLT.execute(rx, ry);
            }
            case "DVD" -> {
                System.out.println("in parser DVD started");
                DVD.execute(rx, ry);
            }case "TRR" -> {
                System.out.println("in parser TRR started");
                TRR.execute(rx, ry);
            }case "AND" -> {
                System.out.println("in parser AND started");
                AND.execute(rx, ry);
            }case "ORR" -> {
                System.out.println("in parser ORR started");
                ORR.execute(rx, ry);
            }case "NOT" -> {
                System.out.println("in parser NOT started");
                NOT.execute(rx);
            }
        }
    }

    private static void executelenOne(String ins, String localopcodestr) {

    }

    private static void executeio(String ins, String localopcodestr) {
    }


    private static void executeShift(String ins, String localopcodestr) {
    }

    public static void executeNormal(String opcodestr, String reg, String indReg, String indirect, String address) {


        String EA = EffectiveAddress.getEA(indirect, indReg, address);
        System.out.println(EA);
        System.out.println("parser executed" + opcodeMap.get(opcodestr));
        switch (opcodestr) {

            case "000000" -> {
                System.out.println("in parser opcode 0 HLT started");
                HLT.execute();
            }

            case "000001" -> {
                System.out.println("in parser opcode 1 LDR started");
                LDR.execute(memory, reg, EA);
            }
            case "000002" -> {
                System.out.println("in parser opcode 2 STR started");
                STR.execute(memory, reg, EA);
            }
            case "000003" -> {
                System.out.println("in parser opcode 3 LDA started");
                LDA.execute(reg, EA);
            }
            case "100001" -> {
                System.out.println("in parser opcode 41 LDX started");
                LDX.execute(memory, indReg, EA);

            }
            case "100010" -> {
                System.out.println("in parser opcode 42 STX started");
                STX.execute(memory, indReg, EA);
            }
            default -> System.out.println("Invalid Opcode");

        }


    }


}
