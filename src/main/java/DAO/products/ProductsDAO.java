package DAO.products;

import DAO.SingletonConnection;
import model.Category;
import model.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO implements IProductsDAO{
    @Override
    public List<Products> findAll() {
        List<Products> productsList = new ArrayList<>();
        try(
                Connection connection = SingletonConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select products.name,price,quantity,color,descripsion,c.id,c.name from products join quanlysanpham.category c on c.id = products.id_category;")
                ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
//                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("descripsion");
                int id_category = resultSet.getInt("id");
                String nameCategory = resultSet.getString("c.name");
                Category category = new Category(id_category,nameCategory);
                Products products = new Products(name,price,quantity,color,description,category);
                productsList.add(products);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsList;
    }


    @Override
    public Products findById(int id) {
        return null;
    }

    @Override
    public boolean update(Products products) {
        return false;
    }

    @Override
    public boolean save(Products products) {
        try(
                Connection c = SingletonConnection.getConnection();
                PreparedStatement preparedStatement = c.prepareStatement("insert into products(name ,price, quantity,color,description,id_category)value (?,?,?,?,?,?)");
                ) {
                preparedStatement.setString(1,products.getName());
                preparedStatement.setDouble(2,products.getPrice());
                preparedStatement.setInt(3,products.getQuantity());
                preparedStatement.setString(4,products.getColor());
                preparedStatement.setString(5,products.getDescripsion());
                preparedStatement.setInt(6,products.getCategory().getId());
                return preparedStatement.executeUpdate() >0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    public static void main(String[] args) {
        ProductsDAO productsDAO = new ProductsDAO();
        System.out.println(productsDAO.findAll());
    }
}
