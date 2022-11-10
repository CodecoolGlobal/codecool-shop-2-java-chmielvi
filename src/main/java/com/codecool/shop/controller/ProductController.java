package com.codecool.shop.controller;


import com.codecool.shop.model.User;
import com.codecool.shop.service.DatabaseManager;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String userName = (session != null) ? (String) session.getAttribute("username") : null;
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        System.out.println("user namemmmm;;   "+userName);
        context.setVariable("username", userName);
        context.setVariable("categories", databaseManager.getAllCategories());
        context.setVariable("products", databaseManager.getAllProducts());
        context.setVariable("suppliers", databaseManager.getAllSuppliers());
        engine.process("product/index.html", context, resp.getWriter());

    }

}


//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//        HttpSession session = request.getSession(false);
//        User user = (session != null) ? (User) session.getAttribute("user") : null;
//        String loginURL = request.getContextPath() + "/login";
//
//        if (user == null && !request.getRequestURI().equals(loginURL)) {
//            response.sendRedirect(loginURL);
//        } else {
//            chain.doFilter(request, response);
//        }
//    }


//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getSession().invalidate();
//        response.sendRedirect(request.getContextPath() + "/login");
//    }