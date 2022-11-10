package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.service.DatabaseManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String message = req.getParameter("message");
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("message", message);
            engine.process("login.html", context, resp.getWriter());
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            System.out.println(username + "  " + password);
            if(databaseManager.getUser(username)){
                if(databaseManager.getUser(username, password)){
                    HttpSession session=request.getSession();
                    session.setAttribute("username",username);
                    response.sendRedirect("/");
                }
                String message = "wrong password, try again!";
                response.sendRedirect("/login?message=" + message);

            }
            String message = "wrong username, try again!";
            response.sendRedirect("/login?message=" + message);

//            doGet(request, response);
        }


    }

