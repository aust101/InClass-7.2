package com.overaitis.inclass_7_2;

import com.overaitis.inclass_7_2.command.sources.CommandResult;
import com.overaitis.inclass_7_2.command.sources.types.Console;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static boolean running = true;
    private static final Scanner scanner = new Scanner(System.in);
    private static Console console;

    public static void main(String[] args) {
        console = new Console();
        registerCommands();
        System.out.println("Server Started!");
        while (running) {
            String input =  scanner.nextLine();
            CommandRegistry.getOrCreate().tryExecute(console, input);
        }
    }

    private static void registerCommands() {
        CommandRegistry.getOrCreate().register("say", (src, args) -> {
            if(args.length == 0)
                return CommandResult.FAILURE;
            src.sendMessage(String.join(" ", args));
            return CommandResult.SUCCESSFUL;
        });

        CommandRegistry.getOrCreate().register("shutdown", (src, args) -> {
            src.sendMessage("SHUTTING SERVER DOWN" + (args.length > 0 ? " - " + String.join(" ", args) : ""));
            running = false;
            return CommandResult.SUCCESSFUL;
        });

        CommandRegistry.getOrCreate().register("message", ((src, args) -> {
            if(args.length >= 2) {
                String target = args[0];
                String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                System.out.println(src.getName() + " has slid into " + target + "'s DMs and said: \"" + message + "\"");
                return CommandResult.SUCCESSFUL;
            }
            return CommandResult.FAILURE;
        }));
    }

    public static Console getConsole() {
        return console;
    }
}
