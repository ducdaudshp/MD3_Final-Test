package DAO.category;

import DAO.SingletonConnection;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO{
    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = SingletonConnection.getConnection();
        try(
                PreparedStatement preparedStatement = connection.prepareStatement("select * from category");
                ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id,name);
                categoryList.add(category);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category findById(int id) {
        Category category = null;
        try(
                Connection c = SingletonConnection.getConnection();
                PreparedStatement preparedStatement = c.prepareStatement("select *from category where id=?");
                ) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                category = new Category(id,name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean update(Category category) {
        return false;
    }

    @Override
    public boolean save(Category category) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();
        System.out.println(categoryDAO.findById(1));
    }
}
