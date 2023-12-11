package com.project.instructions;
import com.project.commons.RegisterMap;

public class NOT {
    static RegisterMap regmap = RegisterMap.getInstance();

    public static void execute(String rx ) {
        String rxKey = regmap.getKey(rx);
        short rxVal = regmap.getValue(rxKey);
        short negrxVal = (short) ~rxVal;

        regmap.setValue(rxKey, negrxVal);

    }

}
