package com.project.instructions;

import com.project.components.panels.ControlPanel;

/**
 * This is the Halt Instruction
 */
public class HLT {

    public static void execute(){
        ControlPanel.setHalt(true);
    }

}
