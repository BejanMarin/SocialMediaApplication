import repository.PostRepository;
import service.CommentService;
import service.PostService;
import service.UserService;
import java.util.Scanner;


public class Controller {

    private static UserService userService = new UserService();
    private static PostService postService =new PostService();
    private static CommentService commentService = new CommentService();
    static Scanner scText = new Scanner(System.in);


    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter the desired flow (user/post/comment):");
            String response = scText.nextLine();
            switch (response) {
                case "user":
                    startUserFlow();
                    break;

                case "post":
                    startPostflow();
                    break;
                case "comment":
                    startCommentflow();
                    break;
                default:
                    System.out.println("This flow does not exist");
            }

        }
    }

    private static void startUserFlow() {
        String chosenOperation = chooseOperation("RA / RBI / C / U / D");
        switch (chosenOperation) {
                case "RA":
                    userService.readAll();
                    break;
                case "RBI":
                    userService.readById();
                    break;
                case "C":
                    userService.create();
                    break;
                case "U":
                    userService.update();
                    break;
                case "D":
                    userService.delete();
                    break;
                default:
                    System.out.println("Invalid operation");
            }
        }


    private static void startPostflow() {
        String chosenOperation = chooseOperation("RA / RUP / C / U / D");
        switch (chosenOperation){
            case "RA":
               postService.readAll();
                break;
            case "RUP":
                postService.readUserPosts();
                break;
            case "C":
                postService.create();
                break;
            case "U":
                postService.update();
                break;
            case "D":
              postService.delete();
                break;
            default:
                System.out.println("Invalid operation");
        }
    }

    private static void startCommentflow() {
        String chosenOperation = chooseOperation(" C / U / D");
        switch (chosenOperation){
            case "C":
                commentService.create();
                break;
            case "U":
                commentService.update();
                break;
            case "D":
               commentService.delete();
                break;
            default:
                System.out.println("Invalid operation");
        }
    }




    private static String chooseOperation(String operations) {
        System.out.println("Enter the desired operation (" + operations +")");
        String chosenOperation = scText.nextLine();
        return chosenOperation;
    }







}

