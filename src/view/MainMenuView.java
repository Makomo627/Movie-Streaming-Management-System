package view;

import controller.CategoryController;
import controller.MovieController;
import controller.UserController;
import java.util.Scanner;

public class MainMenuView {

    private CategoryView categoryView;
    private MovieView movieView;
    private UserView userView;
    private Scanner scanner;

    public MainMenuView() {
        this.scanner = new Scanner(System.in);

        // Initialize Controllers
        CategoryController categoryController = new CategoryController();
        MovieController movieController = new MovieController();
        UserController userController = new UserController();

        // Initialize Views
        this.categoryView = new CategoryView(categoryController, scanner);
        this.movieView = new MovieView(movieController, userController, scanner);
        this.userView = new UserView(userController, scanner);
    }

    public void start() {
        while (true) {
            System.out.println("\n==========================================");
            System.out.println("   ONLINE MOVIE STREAMING SYSTEM (CLI)    ");
            System.out.println("==========================================");
            System.out.println("1. Movie Management & Streaming");
            System.out.println("2. Category Management");
            System.out.println("3. User & Account");
            System.out.println("0. Exit System");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    movieView.displayMenu();
                    break;
                case "2":
                    categoryView.displayMenu();
                    break;
                case "3":
                    userView.displayMenu();
                    break;
                case "0":
                    System.out.println("Thank you for using the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}