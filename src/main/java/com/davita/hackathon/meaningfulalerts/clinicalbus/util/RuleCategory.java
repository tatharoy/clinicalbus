package com.davita.hackathon.meaningfulalerts.clinicalbus.util;

public enum RuleCategory {

    RangeAlert("RangeAlert", "OutSideRange"),
    HigherRangeAlert("HigherRangeAlert", "HigherRange"),
    LowerRangeAlert("LowerRangeAlert", "LowerRange"),
    TrueAlert("TrueAlert", "True"),
    FalseAlert("FalseAlert", "False");

    private String name;
    private String triggerType;


    RuleCategory(String name, String triggerType) {
        this.name = name;
        this.triggerType = triggerType;
    }

    public String getName() {
        return name;
    }

    public String getTriggerType() {
        return triggerType;
    }
}
