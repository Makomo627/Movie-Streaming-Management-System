package view;

import controller.UserController;
import java.util.List;
import java.util.Scanner;
import model.User;
import model.WatchHistoryItem;

public class UserView {

    private UserController userController;
    private Scanner scanner;

    public UserView(UserController userController, Scanner scanner) {
        this.userController = userController;
        this.scanner = scanner;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== USER MANAGEMENT ===");
            if (userController.isLoggedIn()) {
                User current = userController.getCurrentUser();
                System.out.println("Logged in as: " + current.getUsername() + " (ID: " + current.getId() + ")");
                System.out.println("1. View My Watchlist");
                System.out.println("2. View My Favorites");
                System.out.println("3. View Watch History");
                System.out.println("4. Log Out");
            } else {
                System.out.println("1. Register");
                System.out.println("2. Log In");
            }
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            if (userController.isLoggedIn()) {
                switch (choice) {
                    case "1":
                        showWatchlist();
                        break;
                    case "2":
                        showFavorites();
                        break;
                    case "3":
                        showHistory();
                        break;
                    case "4":
                        userController.logout();
                        System.out.println("Logged out successfully!");
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            } else {
                switch (choice) {
                    case "1":
                        registerUser();
                        break;
                    case "2":
                        loginUser();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }
        }
    }

    private void registerUser() {
        System.out.print("Enter User ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        if (userController.register(id, username)) {
            System.out.println("Registration successful! Please log in.");
        } else {
            System.out.println("Registration failed! Duplicate User ID or Username.");
        }
    }

    private void loginUser() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        if (userController.login(username)) {
            System.out.println("Login successful! Welcome back, " + username + ".");
        } else {
            System.out.println("Username not found!");
        }
    }

    private void showWatchlist() {
        User u = userController.getCurrentUser();
        System.out.println("\n--- MY WATCHLIST ---");
        if (u.getWatchlistIds().isEmpty()) {
            System.out.println("Your watchlist is empty.");
        } else {
            for (String id : u.getWatchlistIds()) {
                System.out.println("- Movie ID: " + id);
            }
        }
    }

    private void showFavorites() {
        User u = userController.getCurrentUser();
        System.out.println("\n--- MY FAVORITE MOVIES ---");
        if (u.getFavoriteIds().isEmpty()) {
            System.out.println("Your favorites list is empty.");
        } else {
            for (String id : u.getFavoriteIds()) {
                System.out.println("- Movie ID: " + id);
            }
        }
    }

    private void showHistory() {
        List<WatchHistoryItem> history = userController.getCurrentUserHistory();
        System.out.println("\n--- WATCH HISTORY ---");
        if (history.isEmpty()) {
            System.out.println("No watch history found.");
        } else {
            for (WatchHistoryItem item : history) {
                System.out.println("Movie ID: " + item.getMovieId()
                        + " | Watched At: " + item.getWatchedAt()
                        + " | Stopped At: " + item.getStoppedAtSeconds() + "s");
            }
        }
    }
}