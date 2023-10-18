package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {


    int id;
    int user_id;
    String message;
    LocalDateTime createdAt;
    public ArrayList<Comment> comments;

    public Post(int id, int user_id, String message, LocalDateTime createdAt) {
        this.id = id;
        this.user_id = user_id;
        this.message = message;
        this.createdAt = createdAt;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "\nPost{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", comments=" + comments +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "\nPost{" +
//                "id=" + id +
//                ", user_id=" + user_id +
//                ", message='" + message + '\'' +
//                "', createdAt=" + createdAt +
//                "}";
//    }
}
