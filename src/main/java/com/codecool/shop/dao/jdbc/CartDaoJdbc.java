package com.codecool.shop.dao.jdbc;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DaoJdbc;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;

import javax.smartcardio.Card;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoJdbc implements CartDao, DaoJdbc {

    private static CartDaoJdbc instance = null;
    private DataSource dataSource;


    public static CartDaoJdbc getInstance() {
        if (instance == null) {
            instance = new CartDaoJdbc();
        }
        return instance;
    }

    private CartDaoJdbc() {
    }

    @Override
    public void createCart(Cart cart) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart (user_id) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, cart.getUser().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            cart.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addProductToCart(int userId, int productId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE cart SET product_id = array_append(product_id, ?) WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, productId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }


    @Override
    public Cart find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Cart> getAll() {
        return null;
    }


    public List<Product> getAllProductsFromCart(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT products.*" +
                    "FROM products \n" +
                    "JOIN cart on products.id = ANY(cart.product_id)\n" +
                    "WHERE cart.user_id = ?";
            PreparedStatement prepareStatement = conn.prepareStatement(sql);
            prepareStatement.setInt(1, userId);
            ResultSet resultSet = prepareStatement.executeQuery();

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
                Product product = new Product(name, price, currency, description, image, ProductCategoryDaoJdbc.getInstance().find(category), SupplierDaoJdbc.getInstance().find(supplier));
                product.setId(id);
                result.add(product);
            }
            return result;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void init(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
