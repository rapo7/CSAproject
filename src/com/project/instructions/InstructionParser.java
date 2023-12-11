package com.project.instructions;

import com.project.commons.Memory;
import com.project.components.panels.ControlPanel;
import com.project.components.panels.FlagPanel;
import com.project.components.panels.RegisterPanel;
import com.project.utils.EffectiveAddress;
import com.project.utils.HexParser;

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

    static {
        opcodeMap.put("011000","TRAP");
        opcodeMap.put("000001","LDR");
        opcodeMap.put("000010","STR");
        opcodeMap.put("000011","LDA");
        opcodeMap.put("100001","LDX");
        opcodeMap.put("100010","STX");
        opcodeMap.put("001000","JZ");
        opcodeMap.put("001001","JNE");
        opcodeMap.put("001010","JCC");
        opcodeMap.put("001011","JMA");
        opcodeMap.put("001100","JSR");
        opcodeMap.put("001101","RFS");
        opcodeMap.put("001111","JGE");
        opcodeMap.put("000100","AMR");
        opcodeMap.put("000101","SMR");
        opcodeMap.put("000110","AIR");
        opcodeMap.put("000111","SIR");
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
            executeio(ins, localreg, localopcodestr, localaddress);
        } else if (arithmeticOps.containsKey(localopcodestr)) {
            FlagPanel.clearFlags();
            executeArthmetic(localopcodestr, localreg, localindReg);
        }  else {
            executeNormal(localopcodestr, localreg, localindReg, localindirect, localaddress);
        }
    }

    private static void executeArthmetic(String localopcodestr, String rx, String ry) {
        String opcodeCode = arithmeticOps.get(localopcodestr);
        switch (opcodeCode) {
            case "MLT" -> {
                System.out.println("in parser " + opcodeCode + " started " + "rx : " + rx +" ry : " + ry);
                MLT.execute(rx, ry);
            }
            case "DVD" -> {
                System.out.println("in parser " + opcodeCode + " started " + "rx : " + rx +" ry : " + ry);
                DVD.execute(rx, ry);
            }case "TRR" -> {
                System.out.println("in parser " + opcodeCode + " started " + "rx : " + rx +" ry : " + ry);
                TRR.execute(rx, ry);
            }case "AND" -> {
                System.out.println("in parser " + opcodeCode + " started " + "rx : " + rx +" ry : " + ry);
                AND.execute(rx, ry);
            }case "ORR" -> {
                System.out.println("in parser " + opcodeCode + " started " + "rx : " + rx +" ry : " + ry);
                ORR.execute(rx, ry);
            }case "NOT" -> {
                System.out.println("in parser " + opcodeCode + " started " + "rx : " + rx );
                NOT.execute(rx);
            }
        }
    }


    private static void executeio(String ins, String reg , String localopcodestr, String devidstr) {
        String command = ioOps.get(localopcodestr);
        short devid = Short.parseShort(devidstr);
        if (command.equals("IN")){
            System.out.println("in parser " + command + " started " + "register : " + reg + " devid : " + devid );
            IN.execute(reg, devid);
        }else if (command.equals("OUT")){

            System.out.println("in parser " + command + " started " + "register : " + reg + " devid : " + devid);
            OUT.execute(reg, devid);
        } else {
            ControlPanel.setHalt(true);
            System.out.println("Unknown Operation" + " ioops " + "register : " + reg + " devid : " + devid);
        }
    }


    private static void executeShift(String ins, String localopcodestr) {


        String command = shiftRotateOps.get(localopcodestr);
        String localreg = ins.substring(6, 8);
        int lorr = Integer.parseInt(ins.substring(8, 9));
        int aorl = Integer.parseInt(ins.substring(9, 10));
        int count = Integer.parseInt(ins.substring(12, 16));

        if (command.equals("SRC")){
            System.out.println("in parser " + command + " started " + "A/L : "  + aorl + " L/R : " + lorr + " Count : " + count );
            SRC.execute(localreg, lorr, aorl, count);
        }else if (command.equals("RRC")){
            System.out.println("in parser " + command + " started " + "A/L : "  + aorl + " L/R : " + lorr + " Count : " + count);
            RRC.execute(localreg, lorr, aorl, count);
        } else {
            ControlPanel.setHalt(true);
            System.out.println("Unknown Operation" + " shiftRotops " + "A/L : "  + aorl + " L/R : " + lorr + " Count : " + count);
        }




    }

    public static void executeNormal(String opcodestr, String reg, String indReg, String indirect, String address) {


        String EA = EffectiveAddress.getEA(indirect, indReg, address);
        System.out.println(EA);
        String command = opcodeMap.get(opcodestr);
        System.out.println("parser executed" + command);
        switch (command) {

            case "HLT" -> {
                System.out.println("in parser " + command + " started ");
                HLT.execute();
            }

            case "LDR" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                LDR.execute(memory, reg, EA);
            }
            case "STR" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                STR.execute(memory, reg, EA);
            }
            case "LDA" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                LDA.execute(reg, EA);
            }
            case "LDX" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                LDX.execute(memory, indReg, EA);

            }
            case "SIX" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                STX.execute(memory, indReg, EA);
            }
            case "JZ" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                JZ.execute(reg, EA);
            }
            case "JNE" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                JNE.execute(reg, EA);
            }
            case "JCC" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                JCC.execute(reg, EA);
            }
            case "JMA" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                JMA.execute(EA);
            }
            case "JSR" -> {
                System.out.println("in parser " + command + " started "   + " EA : " + EA);
                JSR.execute(EA);
            }
            case "RFS" -> {
                System.out.println("in parser " + command + " started "   + "address : " + address);
                RFS.execute(address);
            }
            case "JGE" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                JGE.execute(reg, EA);
            }
            case "AIR" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " Immed : " + address);
                AIR.execute(reg, address);
            }
            case "SIR" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " Immed : " + address);
                SIR.execute(reg, address);
            }
            case "AMR" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                AMR.execute(memory, reg, EA);
            }
            case "SMR" -> {
                System.out.println("in parser " + command + " started "   + "register : " + reg + " EA : " + EA);
                SMR.execute(memory, reg, EA);
            }
            default -> System.out.println("Invalid Opcode"+ " ops "   + "register : " + reg + " EA : " + EA);

        }


    }


}
