package com.codecool.shop.controller;


import com.codecool.shop.service.DatabaseManager;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("categories", databaseManager.getAllCategories());
        context.setVariable("products", databaseManager.getAllProducts());
        context.setVariable("suppliers", databaseManager.getAllSuppliers());
        System.out.println(databaseManager.getAllSuppliers());
        engine.process("product/index.html", context, resp.getWriter());
    }

}
