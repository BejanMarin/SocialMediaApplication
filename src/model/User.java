package model;

import java.util.ArrayList;

public class User {

    public int id;
    public String name;
    public String first_name;
    public String email;
    public String phone_number;

    public ArrayList<Post> posts;

    public User(int id, String name, String first_name, String email, String phone_number,ArrayList<Post>posts) {
        this.id = id;
        this.name=name;
        this.first_name=first_name;
        this.email=email;
        this.phone_number=phone_number;
        this.posts=posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", posts=" + posts +
                "}\n";
    }
}
