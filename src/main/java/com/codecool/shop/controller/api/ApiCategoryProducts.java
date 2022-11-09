package com.codecool.shop.controller.api;

import com.codecool.shop.dao.jdbc.DatabaseManager;
import com.codecool.shop.model.ProductCategory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//"api/category?category_id=1/products"

@WebServlet(name = "categoryProductsApi", urlPatterns = {"/api/category/products"}, loadOnStartup = 3)

public class ApiCategoryProducts extends HttpServlet implements JSONConverter {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryId = request.getParameter("category_id");
        System.out.println(categoryId);
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        List<ProductCategory> products = databaseManager.getProductsByCategory(categoryId);
        PrintWriter out = response.getWriter();
        String json = gson.toJson(products);
        response.setContentType("api/json");
        response.setCharacterEncoding("UTF-8");
//        String json = "{\n" +
//                "  \"Herausgeber\": \"Xema\",\n" +
//                "  \"Nummer\": \"1234-5678-9012-3456\",\n" +
//                "  \"Deckung\": 2e+6,\n" +
//                "  \"Waehrung\": \"EURO\",\n" +
//                "  \"Inhaber\":\n" +
//                "  {\n" +
//                "    \"Name\": \"Mustermann\",\n" +
//                "    \"Vorname\": \"Max\",\n" +
//                "    \"maennlich\": true,\n" +
//                "    \"Hobbys\": [\"Reiten\", \"Golfen\", \"Lesen\"],\n" +
//                "    \"Alter\": 42,\n" +
//                "    \"Kinder\": [],\n" +
//                "    \"Partner\": null\n" +
//                "  }\n" +
//                "}";
        out.print(json);
        out.flush();


    }
}
