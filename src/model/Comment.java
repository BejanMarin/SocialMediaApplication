package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Comment {
    int id;
    int post_id;
    int user_id;
    String message;
    LocalDateTime createdAt;

    public ArrayList<Comment> comments;


    public Comment(int id, int post_id, int user_id, String message, LocalDateTime createdAt) {
        this.id = id;
        this.post_id = post_id;
        this.user_id = user_id;
        this.message = message;
        this.createdAt = createdAt;

    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", post_id=" + post_id +
                ", user_id=" + user_id +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
