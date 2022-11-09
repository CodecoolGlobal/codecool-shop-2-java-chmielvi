package com.codecool.shop.config;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.DatabaseManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseManager db = DatabaseManager.getInstance();
        try {
            db.setup();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
