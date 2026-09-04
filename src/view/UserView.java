package view;

import controller.UserController;
import java.util.Scanner;
import model.User;
import model.WatchHistoryItem;
import util.MyLinkedList;

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
                System.out.println("4. View Playback Queue Size");
                System.out.println("5. Undo/View Recent Action");
                System.out.println("6. Log Out");
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
                        showQueueSize();
                        break;
                    case "5":
                        undoRecentAction();
                        break;
                    case "6":
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
        System.out.print("Enter User ID (format: UXXX, e.g., U001): ");
        String id = scanner.nextLine().trim();

        if (!userController.isValidUserId(id)) {
            System.out.println("Invalid User ID format! Must start with 'U' followed by 3 digits (e.g., U001, U005).");
            return;
        }

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
        MyLinkedList<WatchHistoryItem> history = userController.getCurrentUserHistory();
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

    private void showQueueSize() {
        User u = userController.getCurrentUser();
        System.out.println("Current movies in Playback Queue: " + u.getPlaybackQueue().size());
    }

    private void undoRecentAction() {
        String action = userController.popLastAction();
        if (action != null) {
            System.out.println("[STACK POP] Popped last action: " + action);
        } else {
            System.out.println("No recent action in stack.");
        }
    }
}