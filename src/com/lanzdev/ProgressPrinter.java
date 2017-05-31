package com.lanzdev;

import com.lanzdev.util.ProgressCortege;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ProgressPrinter extends SimpleTagSupport {

    ProgressCortege cortege;

    public ProgressCortege getCortege( ) {
        return cortege;
    }

    public void setCortege(ProgressCortege cortege) {
        this.cortege = cortege;
    }

    @Override
    public void doTag( ) throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.print("<td>" + cortege.getCourse().getName() + "</td>");
        out.print("<td>" + cortege.getSubject().getName() + "</td>");
        out.print("<td>" + cortege.getCourse().getStartDate() + "</td>");
        out.print("<td>" + cortege.getCourse().getExpirationDate() + "</td>");
        out.print("<td>" + cortege.getJournal().getMark() + "</td>");
    }
}
