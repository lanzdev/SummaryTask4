package com.lanzdev.command.impl;

import com.lanzdev.Path;
import com.lanzdev.command.FrontCommand;
import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.impl.MysqlUserDao;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.User;
import com.lanzdev.util.VerifyUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand extends FrontCommand {

    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    @Override
    protected String doGet( ) {
        return Path.WELCOME_PAGE;
    }

    @Override
    protected String doPost( ) {

        LOGGER.debug("Entering doPost()");

        String path = null;

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        LOGGER.trace("on doPost() login = " + login + "; password = " + password);

        UserDao dao = new MysqlUserDao();
        User user = dao.getByLogin(login);
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");


        if (user == null) {

            String enErrMsg = "Incorrect login.";
            String ruErrMsg = "Неверный логин.";
            setErrorMessage(enErrMsg, ruErrMsg, request);
            LOGGER.trace("User with login " + login + " is not found.");
        } else if (!password.equals(user.getPassword())) {

            String enErrMsg = "Incorrect password.";
            String ruErrMsg = "Неверный пароль.";
            setErrorMessage(enErrMsg, ruErrMsg, request);
            LOGGER.trace("Password " + password + " is wrong.");
        } else if (!VerifyUtil.verify(gRecaptchaResponse)) {
            String enErrMsg = "Captcha invalid!";
            String ruErrMsg = "Капча недействительна!";
            setErrorMessage(enErrMsg, ruErrMsg, request);
            LOGGER.trace("Captcha invalid!");
        }
        else if (user.getBlocked()) {

            String enErrMsg = "Your account is blocked.";
            String ruErrMsg = "Ваш аккаунт заблокирован.";
            setErrorMessage(enErrMsg, ruErrMsg, request);
            LOGGER.trace("User is blocked.");
        } else if (user.getPermission() != Permission.TEACHER){

            LOGGER.trace("on doPost() user with id " + user.getId());
            Permission permission = user.getPermission();
            path = Path.REDIRECT_TO_VIEW_COURSES_LIST + "&adds=new";
            session.setAttribute("user", user);
            session.setAttribute("user_permission", permission);
        } else {
            LOGGER.trace("on doPost() user with id " + user.getId());
            Permission permission = user.getPermission();
            path = Path.REDIRECT_TO_COURSES_LIST_FOR_EVALUATION;
            session.setAttribute("user", user);
            session.setAttribute("user_permission", permission);
        }

        LOGGER.debug("Leaving doPost() " + path);
        return path;
    }

    private void setErrorMessage(String enErrMsg, String ruErrMsg, HttpServletRequest request) {

        String lang = (String) request.getSession().getAttribute("lang");
        String errorMessage = "";
        if (lang == null || lang.equals("en")) {
            errorMessage = enErrMsg;
        } else if (lang.equals("ru")) {
            errorMessage = ruErrMsg;
        }
        request.getSession().setAttribute("error", errorMessage);
        LOGGER.error("errorMessage: " + errorMessage);
    }
}
