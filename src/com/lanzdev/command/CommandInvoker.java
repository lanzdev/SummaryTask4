package com.lanzdev.command;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {

    private static final Logger LOGGER = Logger.getLogger(CommandInvoker.class);

    private static final Map<String, CommandCreator> COMMANDS = new HashMap<>();

    static {

    }

    public static FrontCommand getCommand(String commandName) {

        LOGGER.debug("Entering getCommand(commandName = " + commandName + ")");

        FrontCommand command;
        if (commandName == null) {
            LOGGER.warn("getCommand() input value is null.");
            command = COMMANDS.get("UnknownCommand").create();
        } else if (!COMMANDS.containsKey(commandName)) {
            LOGGER.warn("getCommand() cannot find command with such name: " + commandName);
            command = COMMANDS.get("UnknownCommand").create();
        } else {
            command = COMMANDS.get(commandName).create();
        }

        LOGGER.debug("Leaving getCommand(): " + command.getClass());
        return command;
    }
}
