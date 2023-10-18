package service;

import model.Comment;
import model.Post;
import model.User;
import repository.CommentRepository;
import repository.PostRepository;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CommentService {
    private PostRepository postRepository = new PostRepository();
    private UserRepository userRepository = new UserRepository();
    private CommentRepository commentRepository = new CommentRepository();
    private Scanner scNumber = new Scanner(System.in);
    private Scanner scannerText = new Scanner(System.in);


    public void create() {
        System.out.println("Enter post ID:");
        int postId = scNumber.nextInt();
       Post postRead = postRepository.readById(postId);
        if (postRead == null) {
            System.out.println("There is no post with ID " + postId);
        } else {
            System.out.println("Enter your desired comment:");
            String comment = scannerText.nextLine();
          int userID = 0 ;
            LocalDateTime createdAt = LocalDateTime.now();

            int affectedRows = commentRepository.create( postId,  userID, comment,  createdAt);
            if (affectedRows > 0) {
                System.out.println("The comment has been saved");
            } else {
                System.out.println("The comment could not be saved");
            }
        }
    }

    public void update() {
        System.out.println("Enter the id of the comment you want to update:");
        int commentId = scNumber.nextInt();
        Comment comment = commentRepository.readById(commentId);
        if (comment == null) {
            System.out.println("There is no comment with ID " + commentId);
        } else {
            System.out.println("Enter the new comment");
            String newComment = scannerText.nextLine();
            int affectedRows = commentRepository.update(commentId, newComment);
            if (affectedRows > 0) {
                System.out.println("The comment has been modified.");
            } else {
                System.out.println("The comment could not be modified.");
            }
        }
    }

    public void delete() {
        System.out.println("Enter the id you want to delete");
        int commentId = scNumber.nextInt();
        int affectedRows = commentRepository.delete(commentId);
        System.out.println("The comment has" + (affectedRows == 0 ? "not " : "") + " been deleted");
    }
}
