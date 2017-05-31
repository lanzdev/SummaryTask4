package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;

public class UnknownCommand extends FrontCommand {

    @Override
    protected String doGet( ) {
        return Path.WELCOME_PAGE;
    }

    @Override
    protected String doPost( ) {
        return Path.WELCOME_PAGE;
    }
}
