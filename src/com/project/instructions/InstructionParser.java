package com.project.instructions;

import com.project.commons.Memory;
import com.project.utils.EffectiveAddress;

public class InstructionParser {
    static Memory memory = Memory.getInstance();

    public static void parse(String opcodestr, String reg, String indReg, String indirect, String address) {
        int opcode = Integer.parseInt(opcodestr, 2);
        String EA = EffectiveAddress.getEA(indirect, indReg, address);
        System.out.println(EA);
        System.out.println("parser executed" + opcode);
        switch (opcode) {
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
