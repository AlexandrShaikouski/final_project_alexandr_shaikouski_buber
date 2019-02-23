package com.alexshay.buber.controller.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Command Provider
 */
public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<String, Command> commandMap = new HashMap<>();

    public static CommandProvider getInstance() {
        return instance;
    }

    private CommandProvider() {
        commandMap.put("CommandExample", new CommandExample());
        commandMap.put("list_users", new CommandGetUserList());
        commandMap.put("list_drivers", new CommandGetDriverList());
        commandMap.put("create_user", new CommandCreateUser());
        commandMap.put("delete_user", new CommandDeleteUser());
    }

    /**
     * Return command by name
     * @param command name
     * @return command implementation
     */
    public Command takeCommand(String command) {
        return commandMap.get(command);
    }
}
