package com.overaitis.inclass_7_2.command.sources;

import com.overaitis.inclass_7_2.CommandRegistry;

public interface CommandSource {
    String getName();
    default void sendMessage(String... messages) {
        for (String message : messages) {
            sendMessage(message);
        }
    }
    void sendMessage(String message);
    boolean isOperator();
    default void runCommand(String command) {
        CommandRegistry.getOrCreate().tryExecute(this, command);
    }
}
