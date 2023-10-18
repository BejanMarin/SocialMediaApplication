package service;

import model.User;
import repository.PostRepository;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {

    private UserRepository userRepository = new UserRepository();
    private PostRepository postRepository = new PostRepository();
    private CommentService commentService = new CommentService();
    private Scanner scannerNumber = new Scanner(System.in);
    private Scanner scannerText = new Scanner(System.in);
    private Scanner scannerBoolean = new Scanner(System.in);

    public void readAll() {
        ArrayList<User> allUsers = userRepository.readAll();
        for (User user : allUsers) {
            user.posts =postRepository.readPostsFromUserWhitId(user.id);
        }
        allUsers.forEach(user -> System.out.println(user));
    }

    public void readById() {
        System.out.println("Enter the desired user ID:");
        int id = scannerNumber.nextInt();
        User userRead = userRepository.readById(id);
        if (userRead == null) {
            System.out.println("There is no user with read ID " + id);
        } else {
            userRead.posts=postRepository.readPostsFromUserWhitId(id);
            System.out.println(userRead);
        }
    }

    public void create() {
        System.out.println("Enter the name:");
        String name = scannerText.nextLine();
        System.out.println("Enter the first name:");
        String firstName = scannerText.nextLine();
        System.out.println("Enter email:");
        String email = scannerText.nextLine();
        System.out.println("Enter the phone number:");
        String phoneNumber = scannerText.nextLine();
        boolean success = userRepository.create(name, firstName, email, phoneNumber);
        if (success) {
            System.out.println("User saved");
        } else {
            System.out.println("An error occurred");
        }
    }

    public void update() {
        System.out.println("Enter the user id: ");
        int id = scannerNumber.nextInt();
        User userRead = userRepository.readById(id);
        if (userRead != null) {
            boolean weChangeTheName = weModifyTheProperty("name");
            boolean weChangeTheFirstName = weModifyTheProperty("firstName");
            boolean weChangeTheEmail = weModifyTheProperty("email");
            boolean weChangeThePhoneNumber = weModifyTheProperty("phoneNumber");

            if (weChangeTheName) {
                System.out.println("Enter the new name:");
                String newName = scannerText.nextLine();
                userRepository.modifyName(id, newName);
            }

            if (weChangeTheFirstName) {
                System.out.println("Enter the new first name:");
                String newFirstName = scannerText.nextLine();
                userRepository.modifyFirstName(id, newFirstName);
            }

            if (weChangeTheEmail) {
                System.out.println("Enter the new email:");
                String newEmail = scannerText.nextLine();
                userRepository.modifyEmail(id, newEmail);
            }

            if (weChangeThePhoneNumber) {
                System.out.println("Enter the new phone number:");
                String newPhoneNumber = scannerText.nextLine();
                userRepository.modifyPhoneNumber(id, newPhoneNumber);
            }

        } else {
            System.out.println("There is no user with read ID " + id);
        }
    }

    public boolean weModifyTheProperty(String property) {
        System.out.println("Do you want to change the property " + property + " ?");
        return scannerBoolean.nextBoolean();
    }

    public void delete() {
        System.out.println("Enter the ID of the user you want to delete:");
        int id = scannerNumber.nextInt();
        boolean success = userRepository.delete(id);
        System.out.println(success ? "User has been deleted" : "User has been not deleted");
    }


}
