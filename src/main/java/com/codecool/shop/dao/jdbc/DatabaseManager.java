package com.codecool.shop.dao.jdbc;

import com.codecool.shop.config.ConnectionProperties;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.memory.ProductCategoryDaoMem;
import com.codecool.shop.dao.memory.ProductDaoMem;
import com.codecool.shop.dao.memory.SupplierDaoMem;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

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

    public List<Product> getProductsByCategory(ProductCategory productCategory) {
        return productDao.getBy(productCategory);
    }

    public List<Product> getProductsBySupplier(Supplier supplier) {
        return productDao.getBy(supplier);
    }


    public void saveProductCategory(ProductCategory category){
        productCategoryDao.add(category);
    }

//    public void savePlayer(Player player) {
//        PlayerModel model = PlayerModel.getPlayerModel();
//        model.setUp(player);
//        playerDao.add(model);
//    }
//
//    public void saveGameState(GameState gameState) {
//        gameStateDao.add(gameState);
//    }
//
//    public void saveInventory(Inventory inventory) {
//        System.out.println("saveing  inventory");
//        inventoryDao.add(inventory);
//    }
//
//    public Inventory getInventory(int playersId){
//        return inventoryDao.get(playersId);
//    }
//
//
//    public GameState getExistingGame(int id) {
//        return gameStateDao.get(id);
//    }
//
//    public Maps getMap(int id) {
//        System.out.println("get map "+mapsDao.get(id));
//        return mapsDao.get(id);
//    }
//
//
//    public List<GameState> getAllCurrentGameState() {
//        return gameStateDao.getAll();
//    }

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
