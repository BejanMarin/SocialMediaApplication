package service;

import model.Comment;
import model.Post;
import model.User;
import repository.CommentRepository;
import repository.PostRepository;
import repository.UserRepository;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class PostService {

    private PostRepository postRepository = new PostRepository();
    private UserRepository userRepository = new UserRepository();
    private CommentRepository commentRepository = new CommentRepository();
    private Scanner scNumber = new Scanner(System.in);
    private Scanner scannerText = new Scanner(System.in);

    public void readAll() {
        ArrayList<Post> allPosts = postRepository.readAll();
        ArrayList<Comment> allComments = commentRepository.readAll();
        for (Post post : allPosts) {
            System.out.println(post);
        }
        for (Comment comment : allComments){
            System.out.println(comment);
        }


    }

    public void readUserPosts() {
        System.out.println("Enter the user's ID to return his posts");
        int userId = scNumber.nextInt();
        User userRead = userRepository.readById(userId);
        Comment c = commentRepository.readById(userId);
        if (userRead == null) {
            System.out.println("There is no user with ID " + userId);
        } else {
            ArrayList<Post> userPosts = postRepository.readPostsFromUserWhitId(userId);
            userPosts.forEach(post -> System.out.println(post));

            ArrayList<Comment> allComments = commentRepository.readAll();
            allComments.forEach(comment -> System.out.println(comment));
        }


    }

    public void create() {
        System.out.println("Enter user ID:");
        int userId = scNumber.nextInt();
        User userRead = userRepository.readById(userId);
        if (userRead == null) {
            System.out.println("There is no user with ID " + userId);
        } else {
            System.out.println("Enter your desired message:");
            String message = scannerText.nextLine();
            LocalDateTime createdAt = LocalDateTime.now();
            int affectedRows = postRepository.create(userId, message, createdAt);
            if (affectedRows > 0) {
                System.out.println("The post has been saved");
            } else {
                System.out.println("The post could not be saved");
            }
        }
    }

    public void update() {
        System.out.println("Enter the post id:");
        int postId = scNumber.nextInt();
        Post post = postRepository.readById(postId);
        if (post == null) {
            System.out.println("There is no user with ID " + postId);
        } else {
            System.out.println("Enter the new message");
            String newMessage = scannerText.nextLine();
            int affectedRows = postRepository.update(postId, newMessage);
            if (affectedRows > 0) {
                System.out.println("The post has been modified.");
            } else {
                System.out.println("The post could not be modified.");
            }
        }
    }

    public void delete() {
        System.out.println("Enter the id you want to delete");
        int postId = scNumber.nextInt();
        int affectedRows = postRepository.delete(postId);
        System.out.println("The post has" + (affectedRows == 0 ? "not" : "") + " been deleted");
    }
}
