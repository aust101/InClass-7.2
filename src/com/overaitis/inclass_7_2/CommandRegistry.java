package com.overaitis.inclass_7_2;

import com.overaitis.inclass_7_2.command.Command;
import com.overaitis.inclass_7_2.command.sources.CommandResult;
import com.overaitis.inclass_7_2.command.sources.CommandSource;
import com.overaitis.inclass_7_2.command.sources.types.Console;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private static CommandRegistry instance;
    private Map<String, Command> commandMap;

    public static synchronized CommandRegistry getOrCreate() {
        if(instance == null) {
            instance = new CommandRegistry();
            instance.commandMap = new HashMap<>();

            instance.commandMap.put("help", (src, args) -> {
                String commands = "";
                for (String cmd : instance.commandMap.keySet()) {
                    commands = commands + "\n\t- " + cmd;
                }
                src.sendMessage("Registered Commands:" + commands);
                return CommandResult.SUCCESSFUL;
            });
        }
        return instance;
    }

    public Command register(String alias, Command executor) {
        System.out.println("[CommandRegistry] - registering \"" + alias + "\" command!");
        return this.commandMap.put(alias.toLowerCase(), executor);
    }

    public Command unregister(String alias) {
        return this.commandMap.remove(alias.toLowerCase());
    }

    public void tryExecute(CommandSource source, String command) {
        String[] split = command.split(" ");
        if(split.length > 0) {
            String cmd = split[0];
            if(commandMap.containsKey(cmd.toLowerCase())) {
                String[] args = Arrays.copyOfRange(split, 1, split.length);
                if(commandMap.get(cmd).execute(source, args).equals(CommandResult.SUCCESSFUL))
                    return;
            }
            source.sendMessage(source.getName() + " has ran an unknown command! Use the \"help\" command for a full list");
        }
    }
}
