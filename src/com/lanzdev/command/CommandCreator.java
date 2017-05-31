package com.lanzdev.command;

/**
 * Defines method for creating {@link FrontCommand}
 * Used in {@link CommandInvoker#COMMANDS}
 */
public interface CommandCreator {

    public FrontCommand create();
}
