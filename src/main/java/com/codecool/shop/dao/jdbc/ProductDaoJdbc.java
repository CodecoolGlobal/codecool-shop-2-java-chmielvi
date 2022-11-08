package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.DaoJdbc;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao, DaoJdbc {

    private static ProductDaoJdbc instance = null;
    private DataSource dataSource;

    private ProductDaoJdbc() {}

    public static ProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductDaoJdbc();
        }
        return instance;
    }

    public void init(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM products";
            ResultSet resultSet = conn.createStatement().executeQuery(sql);

            List<Product> result = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                BigDecimal price = resultSet.getBigDecimal("price");
                String currency = resultSet.getString("currency");
                String image = resultSet.getString("image");
                int category = resultSet.getInt("product_category_id");
                int supplier = resultSet.getInt("supplier_id");

                Product product = new Product(name, price, currency, description, image, ProductCategoryDaoJdbc.getInstance().find(category), SupplierDaoJdbc.getInstance().find(supplier) );
                product.setId(id);
                result.add(product);
            }
            return result;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}


