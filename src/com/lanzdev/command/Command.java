package com.lanzdev.command;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 *
 */
public interface Command {

    /**
     *
     * @return path for next manipulations with it from outside
     * @throws ServletException
     * @throws IOException
     */
    String execute() throws ServletException, IOException;
}
