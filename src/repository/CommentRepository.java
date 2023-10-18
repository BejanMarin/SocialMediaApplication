package repository;

import model.Comment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommentRepository {


    public int create(int postId, int userID, String comment, LocalDateTime createdAt) {
        Connection c = ConnectionSingleton.getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String template ="INSERT INTO comment(post_id,user_id,message, year,month,day,hour,minute) VALUES (%d,%d,'$s',%d," +
                    "%d,%d,%d,%d)";
            return st.executeUpdate(String.format(template,
                    postId,
                    userID,
                    comment,
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

    public Comment readById(int commentId) {
        Connection c = ConnectionSingleton.getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("SELECT * FROM comment WHERE id =" + commentId);
            rs.next();
            return new Comment(commentId, rs.getInt("post_id"),
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

    public int update(int commentId, String newComment) {
        Connection c = ConnectionSingleton.getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            String template = "UPDATE comment SET message = '%s' WHERE id '%d";
            return st.executeUpdate(String.format(template, newComment, commentId));
        } catch (SQLException e) {
            return 0;
        }
    }

    public int delete(int commentId) {
        Connection c = ConnectionSingleton.getInstance().getConnection();
        try {
            Statement st = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            return st.executeUpdate("DELETE FROM comment WHERE ID=" + commentId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Comment> readAll() {
        ArrayList<Comment> allComment = new ArrayList<>();
        for (Comment comment : allComment) {
            System.out.println(comment);
        }
        return allComment;
    }


}
