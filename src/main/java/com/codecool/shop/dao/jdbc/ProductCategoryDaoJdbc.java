package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.DaoJdbc;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.memory.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao, DaoJdbc {

    private static ProductCategoryDaoJdbc instance = null;

    private DataSource dataSource;

    private ProductCategoryDaoJdbc() {}

    public static ProductCategoryDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJdbc();
        }
        return instance;
    }

    public void init(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO product_categories (name, description, department) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getDepartment());
//            statement.setInt(4, state.getPlayer().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
