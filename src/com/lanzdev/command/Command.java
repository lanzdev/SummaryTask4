package com.lanzdev.command;

import javax.servlet.ServletException;
import java.io.IOException;

public interface Command {

    String execute() throws ServletException, IOException;
}
