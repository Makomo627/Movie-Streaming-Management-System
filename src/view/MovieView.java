package view;

import controller.MovieController;
import controller.UserController;
import java.util.Scanner;
import model.Movie;
import util.MyLinkedList;

public class MovieView {

    private MovieController movieController;
    private UserController userController;
    private Scanner scanner;

    public MovieView(MovieController movieController, UserController userController, Scanner scanner) {
        this.movieController = movieController;
        this.userController = userController;
        this.scanner = scanner;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== MOVIE MANAGEMENT & STREAMING ===");
            System.out.println("1. View All Movies");
            System.out.println("2. Search Movies by Title");
            System.out.println("3. Filter Movies by Genre");
            System.out.println("4. Add Movie to Watchlist");
            System.out.println("5. Add Movie to Favorites");
            System.out.println("6. Add Movie to Playback Queue");
            System.out.println("7. Play Next Movie in Queue");
            System.out.println("8. Watch Movie directly (Record History)");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    showAllMovies();
                    break;
                case "2":
                    searchMovie();
                    break;
                case "3":
                    filterMovieByGenre();
                    break;
                case "4":
                    addToWatchlist();
                    break;
                case "5":
                    addToFavorites();
                    break;
                case "6":
                    addToPlaybackQueue();
                    break;
                case "7":
                    playNextInQueue();
                    break;
                case "8":
                    watchMovie();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    public void showAllMovies() {
        MyLinkedList<Movie> movies = movieController.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("No movies found in the system.");
            return;
        }
        System.out.println("\n--- MOVIE LIST ---");
        for (Movie m : movies) {
            System.out.println(m.toString());
        }
    }

    private void searchMovie() {
        System.out.print("Enter movie title keyword: ");
        String keyword = scanner.nextLine();
        MyLinkedList<Movie> results = movieController.searchByTitle(keyword);
        if (results.isEmpty()) {
            System.out.println("No matching movies found.");
        } else {
            System.out.println("\n--- SEARCH RESULTS ---");
            for (Movie m : results) {
                System.out.println(m.toString());
            }
        }
    }

    private void filterMovieByGenre() {
        System.out.print("Enter Genre name: ");
        String genre = scanner.nextLine();
        MyLinkedList<Movie> results = movieController.filterByGenre(genre);
        if (results.isEmpty()) {
            System.out.println("No movies found for this genre.");
        } else {
            System.out.println("\n--- MOVIES IN GENRE: " + genre.toUpperCase() + " ---");
            for (Movie m : results) {
                System.out.println(m.toString());
            }
        }
    }

    private void addToWatchlist() {
        if (!userController.isLoggedIn()) {
            System.out.println("Please log in first to use this feature!");
            return;
        }
        System.out.print("Enter Movie ID to add to Watchlist: ");
        String movieId = scanner.nextLine().trim();
        if (movieController.findById(movieId) == null) {
            System.out.println("Movie ID does not exist!");
            return;
        }
        if (userController.addToWatchlist(movieId)) {
            System.out.println("Movie added to Watchlist successfully!");
        } else {
            System.out.println("Movie is already in your Watchlist!");
        }
    }

    private void addToFavorites() {
        if (!userController.isLoggedIn()) {
            System.out.println("Please log in first to use this feature!");
            return;
        }
        System.out.print("Enter Movie ID to add to Favorites: ");
        String movieId = scanner.nextLine().trim();
        if (movieController.findById(movieId) == null) {
            System.out.println("Movie ID does not exist!");
            return;
        }
        if (userController.addToFavorites(movieId)) {
            System.out.println("Movie added to Favorites successfully!");
        } else {
            System.out.println("Movie is already in your Favorites!");
        }
    }

    private void addToPlaybackQueue() {
        if (!userController.isLoggedIn()) {
            System.out.println("Please log in first!");
            return;
        }
        System.out.print("Enter Movie ID to enqueue: ");
        String movieId = scanner.nextLine().trim();
        if (movieController.findById(movieId) == null) {
            System.out.println("Movie ID does not exist!");
            return;
        }
        userController.addToPlaybackQueue(movieId);
        System.out.println("Movie added to Playback Queue!");
    }

    private void playNextInQueue() {
        if (!userController.isLoggedIn()) {
            System.out.println("Please log in first!");
            return;
        }
        String movieId = userController.playNextInQueue();
        if (movieId == null) {
            System.out.println("Playback queue is empty!");
            return;
        }
        Movie movie = movieController.findById(movieId);
        if (movie != null) {
            movie.incrementViews();
            userController.recordWatchHistory(movieId, 0);
            System.out.println("Now playing from Queue: " + movie.getTitle());
        }
    }

    private void watchMovie() {
        if (!userController.isLoggedIn()) {
            System.out.println("Please log in first to watch movies!");
            return;
        }
        System.out.print("Enter Movie ID to watch: ");
        String movieId = scanner.nextLine().trim();
        Movie movie = movieController.findById(movieId);
        if (movie == null) {
            System.out.println("Movie ID does not exist!");
            return;
        }

        System.out.print("Enter playback duration in seconds (stopped mark): ");
        try {
            int seconds = Integer.parseInt(scanner.nextLine().trim());
            movie.incrementViews();
            userController.recordWatchHistory(movieId, seconds);
            System.out.println("Now playing: " + movie.getTitle() + "... Watch history recorded!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid duration format!");
        }
    }
}