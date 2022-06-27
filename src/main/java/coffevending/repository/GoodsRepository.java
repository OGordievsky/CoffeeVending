package coffevending.repository;

import coffevending.model.Product;
import coffevending.repository.dbconnection.DBConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsRepository {
    private final DBConnection dataBaseConnection = new DBConnection();

    public Product get(String stId) {
        String sql = "SELECT * FROM goods WHERE id = ?";
        try (PreparedStatement prSt = dataBaseConnection.getDbConnection().prepareStatement(sql)) {
            Long index = Long.parseLong(stId);
            prSt.setLong(1, index);
            ResultSet set = prSt.executeQuery();
            if (set.next()) {
                return new Product(
                        set.getLong("id"),
                        set.getLong("ean"),
                        set.getString("name"),
                        set.getBigDecimal("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean add(Product product) {
        String sql = "INSERT INTO goods (ean, name,price) " +
                "VALUES (?, ?, ?)";
        int result = 0;
        try (PreparedStatement prSt = dataBaseConnection.getDbConnection().prepareStatement(sql)) {
            prSt.setLong(1, product.getEan());
            prSt.setString(2, product.getName());
            prSt.setBigDecimal(3, product.getPrice());
            result = prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public List<Product> getAll() {
        String sql = "SELECT * FROM goods";
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement prSt = dataBaseConnection.getDbConnection().prepareStatement(sql)) {
            ResultSet set = prSt.executeQuery();
            while (set.next()) {
                Product product = new Product(
                        set.getLong("id"),
                        set.getLong("ean"),
                        set.getString("name"),
                        set.getBigDecimal("price"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public boolean update(Product product) {
        String sql = "UPDATE goods SET ean = ?, name = ?, price = ?" +
                "WHERE id = ?";
        int result = 0;
        try (PreparedStatement prSt = dataBaseConnection.getDbConnection().prepareStatement(sql)) {
            prSt.setLong(1, product.getEan());
            prSt.setString(2, product.getName());
            prSt.setBigDecimal(3, product.getPrice());
            prSt.setLong(4, product.getId());
            result = prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM goods WHERE id = ?";
        try (PreparedStatement prSt = dataBaseConnection.getDbConnection().prepareStatement(sql)) {
            prSt.setLong(1, id);
            return prSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
