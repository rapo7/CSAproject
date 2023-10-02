package com.project.instructions;

import com.project.commons.Memory;
import com.project.utils.EffectiveAddress;

/**
 * This class has all the code to parse and execute instruction base don opcode
 */
public class InstructionParser {
    static Memory memory = Memory.getInstance();

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

        parse(localopcodestr, localreg, localindReg, localindirect, localaddress);


    }

    public static void parse(String opcodestr, String reg, String indReg, String indirect, String address) {


        int opcode = Integer.parseInt(opcodestr, 2);
        String EA = EffectiveAddress.getEA(indirect, indReg, address);
        System.out.println(EA);
        System.out.println("parser executed" + opcode);
        switch (opcode) {

            case 0 -> {
                System.out.println("in parser opcode 0 HLT started");
                HLT.execute();
            }

            case 1 -> {
                System.out.println("in parser opcode 1 LDR started");
                LDR.execute(memory, reg, EA);
            }
            case 2 -> {
                System.out.println("in parser opcode 2 STR started");
                STR.execute(memory, reg, EA);
            }
            case 3 -> {
                System.out.println("in parser opcode 3 LDA started");
                LDA.execute(reg, EA);
            }
            case 41 -> {
                System.out.println("in parser opcode 41 LDX started");
                LDX.execute(memory, indReg, EA);
            }
            case 42 -> {
                System.out.println("in parser opcode 42 STX started");
                STX.execute(memory, indReg, EA);
            }


        }


    }


}
