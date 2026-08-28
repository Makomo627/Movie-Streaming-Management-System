package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Category;
import model.Movie;
import model.User;
import model.WatchHistoryItem;

public class FileStorage {
    private static final String DATA_DIR = "data/";

    private static void ensureDirectoryExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static List<Category> loadCategories() {
        List<Category> list = new ArrayList<>();
        File file = new File(DATA_DIR + "categories.txt");
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Category c = Category.fromFileString(line);
                if (c != null) {
                    list.add(c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void saveCategories(List<Category> list) {
        ensureDirectoryExists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_DIR + "categories.txt"))) {
            for (Category c : list) {
                bw.write(c.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Movie> loadMovies() {
        List<Movie> list = new ArrayList<>();
        File file = new File(DATA_DIR + "movies.txt");
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Movie m = Movie.fromFileString(line);
                if (m != null) {
                    list.add(m);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void saveMovies(List<Movie> list) {
        ensureDirectoryExists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_DIR + "movies.txt"))) {
            for (Movie m : list) {
                bw.write(m.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> loadUsers() {
        List<User> list = new ArrayList<>();
        File file = new File(DATA_DIR + "users.txt");
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length >= 2) {
                    User u = new User(p[0].trim(), p[1].trim());
                    if (p.length > 2 && !p[2].trim().isEmpty()) {
                        u.getWatchlistIds().addAll(Arrays.asList(p[2].trim().split(";")));
                    }
                    if (p.length > 3 && !p[3].trim().isEmpty()) {
                        u.getFavoriteIds().addAll(Arrays.asList(p[3].trim().split(";")));
                    }
                    list.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void saveUsers(List<User> list) {
        ensureDirectoryExists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_DIR + "users.txt"))) {
            for (User u : list) {
                String w = String.join(";", u.getWatchlistIds());
                String f = String.join(";", u.getFavoriteIds());
                bw.write(u.getId() + "," + u.getUsername() + "," + w + "," + f);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<WatchHistoryItem> loadWatchHistory() {
        List<WatchHistoryItem> list = new ArrayList<>();
        File file = new File(DATA_DIR + "history.txt");
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                WatchHistoryItem h = WatchHistoryItem.fromFileString(line);
                if (h != null) {
                    list.add(h);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void saveWatchHistory(List<WatchHistoryItem> list) {
        ensureDirectoryExists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_DIR + "history.txt"))) {
            for (WatchHistoryItem h : list) {
                bw.write(h.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}