package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class UserRepository {


    public ArrayList<User> readAll() {
        ArrayList<User> allUsers = new ArrayList<>();
        Connection c = ConnectionSingleton.getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                allUsers.add(extractUserFromResulSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }


    public User readById(int id) {
        User userRead = null;
        Connection c = ConnectionSingleton.getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM user WHERE id=" + id);
            rs.next();
            userRead = extractUserFromResulSet(rs);
        } catch (SQLException e) {
            return null;
        }
        return userRead;

    }

    public boolean create(String name, String firstName, String email, String phoneNumber) {
        Connection c = ConnectionSingleton.getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String template = "INSERT INTO user (name, first_name, email, phone_number) VALUES ('%s','%s','%s','%s');";
            int affectedRows = st.executeUpdate(String.format(template, name, firstName, email, phoneNumber));
            return affectedRows > 0;

        } catch (SQLException e) {
            System.out.println("The user was not saved");
            return false;
        }
    }

    public void modifyName(int id, String newName) {
        modifyColumn(id, newName, "name");
    }

    public void modifyFirstName(int id, String newFirstName) {
        modifyColumn(id, newFirstName, "first_name");
    }


    public void modifyEmail(int id, String newEmail) {
        modifyColumn(id, newEmail, "email");
    }

    public void modifyPhoneNumber(int id, String newPhoneNumber) {
        modifyColumn(id, newPhoneNumber, "phone_number");
    }

    public boolean delete(int id) {
        Connection c = ConnectionSingleton.getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            int affectedRows = st.executeUpdate("DELETE FROM user WHERE id = " + id);
            return affectedRows > 0;
        } catch (SQLException e) {
            return false;
        }

    }


    private static User extractUserFromResulSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("first_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                new ArrayList<>()
        );
    }


    private static void modifyColumn(int id, String newValue, String columnName) {
        Connection c = ConnectionSingleton.getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String template = "UPDATE user SET %s = '%s' WHERE id = '%d'";
            int affectedRows = st.executeUpdate(String.format(template, columnName, newValue, id));
            System.out.println(columnName + "  " + (affectedRows > 0 ? " Changed" : "Unmodified"));
        } catch (SQLException e) {
            System.out.println("The column could not be modified");
        }
    }

}
