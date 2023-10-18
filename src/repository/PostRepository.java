package repository;

import model.Comment;
import model.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static repository.ConnectionSingleton.getInstance;

public class PostRepository {


    public ArrayList<Post> readPostsFromUserWhitId(int userId) {
        ArrayList<Post> userPosts = new ArrayList<>();
        Connection c = getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("SELECT * FROM post WHERE user_id =" + userId);
            while (rs.next()) {
                userPosts.add(extractPostFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return userPosts;


    }

    private Post extractPostFromResultSet(ResultSet rs) {
        try {
            return new Post(rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("message"),
                    LocalDateTime.of(rs.getInt("year"),
                            rs.getInt("month"),
                            rs.getInt("day"),
                            rs.getInt("hour"),
                            rs.getInt("minute")

                    ));
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Post> readAll() {
        ArrayList<Post> allPosts = new ArrayList<>();
        Connection c = getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM post");
            while (rs.next()) {
                allPosts.add(extractPostFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allPosts;
    }

    public int create(int userId, String message, LocalDateTime createdAt) {
        Connection c = getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String template = "INSERT INTO post(user_id,message, year,month,day,hour,minute) VALUES (%d,'$s',%d," +
                    "%d,%d,%d,%d)";
            return st.executeUpdate(String.format(template,
                    userId,
                    message,
                    createdAt.getYear(),
                    createdAt.getMonthValue(),
                    createdAt.getDayOfMonth(),
                    createdAt.getHour(),
                    createdAt.getMinute()
            ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Post readById(int postId) {
        Connection c = getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM post WHERE id =" + postId);
            rs.next();
            return new Post(postId, rs.getInt("user_id"),
                    rs.getString("message"),
                    LocalDateTime.of(rs.getInt("year"),
                            rs.getInt("month"),
                            rs.getInt("day"),
                            rs.getInt("hour"),
                            rs.getInt("minute")
                            ));

        } catch (SQLException e) {
            return null;
        }

    }

    public int update(int postId, String newMessage) {
        Connection c = getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String template ="UPDATE post SET message = '%s' WHERE id '%d";
           return st.executeUpdate(String.format(template,newMessage,postId));
        } catch (SQLException e) {
           return 0;
        }
    }

    public int delete(int postId) {
        Connection c = getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
           return st.executeUpdate("DELETE FROM post WHERE ID=" + postId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
