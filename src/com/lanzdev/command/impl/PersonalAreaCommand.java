package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import org.apache.log4j.Logger;

public class PersonalAreaCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(PersonalAreaCommand.class);

    @Override
    protected String doGet( ) {
        return Path.FORWARD_TO_PERSONAL_AREA;
    }

    @Override
    protected String doPost( ) {
        return Path.REDIRECT_TO_PERSONAL_AREA;
    }
}
