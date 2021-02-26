package com.overaitis.inclass_7_2.command.sources.types;

import com.overaitis.inclass_7_2.command.sources.CommandSource;

public class Console implements CommandSource {
    @Override
    public String getName() {
        return "Console";
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("CONSOLE BROADCAST: " + message);
    }

    @Override
    public boolean isOperator() {
        return true;
    }
}
