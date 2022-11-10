package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;
import com.codecool.shop.service.DatabaseManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/shopping-cart"})
public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession(false);
        String userName = (session != null) ? (String) session.getAttribute("username") : null;
        if (userName != null) {
            User user = databaseManager.getUserObject(userName);
            System.out.println("CART PRODUCTS");
            //List<Product> products = databaseManager.getAllProductsFromCart(user.getId());
            //System.out.println(databaseManager.getAllProductsFromCart(user.getId()));
            Map<Product, Integer> productAndAmounts = databaseManager.getAllProductsFromCart(user.getId());
            System.out.println("AMOUNTS " + productAndAmounts);
            context.setVariable("shoppingList", productAndAmounts);

        }

        engine.process("shopping-cart.html", context, resp.getWriter());
    }


}

