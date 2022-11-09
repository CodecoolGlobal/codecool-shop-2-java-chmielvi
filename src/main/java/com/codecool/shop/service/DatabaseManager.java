package com.codecool.shop.service;

import com.codecool.shop.config.ConnectionProperties;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import com.codecool.shop.dao.jdbc.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.jdbc.ProductDaoJdbc;
import com.codecool.shop.dao.jdbc.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.postgresql.ds.PGSimpleDataSource;

public class DatabaseManager {
    private static DatabaseManager instance = null;
    private ProductCategoryDaoJdbc productCategoryDao;
    private ProductDaoJdbc productDao;
    private SupplierDaoJdbc supplierDao;

    public DatabaseManager(){}

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        productCategoryDao =  ProductCategoryDaoJdbc.getInstance();
        productCategoryDao.init(dataSource);
        productDao = ProductDaoJdbc.getInstance();
        productDao.init(dataSource);
        supplierDao = SupplierDaoJdbc.getInstance();
        supplierDao.init(dataSource);
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryDao.getAll();
    }

    public List<Supplier> getAllSuppliers() {
        return supplierDao.getAll();
    }

    public List<Product> getProductsByCategory(int id) {
        return productDao.getByCategory(id);
    }

    public List<Product> getProductsBySupplier(int id) {
        return productDao.getBySupplier(id);
    }


    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        ConnectionProperties connectionProperties = ConnectionProperties.getInstance();
        String dbName = connectionProperties.getDatabase();
        String user = connectionProperties.getUser();
        String password = connectionProperties.getPassword();
        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

}
