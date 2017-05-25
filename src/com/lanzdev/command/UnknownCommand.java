package com.lanzdev.command;

import com.lanzdev.Path;

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
