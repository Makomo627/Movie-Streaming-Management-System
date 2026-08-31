package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.Category;
import model.Movie;
import model.User;
import model.WatchHistoryItem;
import util.MyLinkedList;

public class FileStorage {
    private static final String DATA_DIR = "data/";

    private static void ensureDirectoryExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static MyLinkedList<Category> loadCategories() {
        MyLinkedList<Category> list = new MyLinkedList<>();
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

    public static void saveCategories(MyLinkedList<Category> list) {
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

    public static MyLinkedList<Movie> loadMovies() {
        MyLinkedList<Movie> list = new MyLinkedList<>();
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

    public static void saveMovies(MyLinkedList<Movie> list) {
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

    public static MyLinkedList<User> loadUsers() {
        MyLinkedList<User> list = new MyLinkedList<>();
        File file = new File(DATA_DIR + "users.txt");
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",", -1);
                if (p.length >= 2) {
                    User u = new User(p[0].trim(), p[1].trim());
                    if (p.length > 2 && !p[2].trim().isEmpty()) {
                        for (String wId : p[2].trim().split(";")) {
                            u.getWatchlistIds().add(wId);
                        }
                    }
                    if (p.length > 3 && !p[3].trim().isEmpty()) {
                        for (String fId : p[3].trim().split(";")) {
                            u.getFavoriteIds().add(fId);
                        }
                    }
                    list.add(u);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void saveUsers(MyLinkedList<User> list) {
        ensureDirectoryExists();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_DIR + "users.txt"))) {
            for (User u : list) {
                StringBuilder wBuilder = new StringBuilder();
                for (int i = 0; i < u.getWatchlistIds().size(); i++) {
                    wBuilder.append(u.getWatchlistIds().get(i));
                    if (i < u.getWatchlistIds().size() - 1) wBuilder.append(";");
                }

                StringBuilder fBuilder = new StringBuilder();
                for (int i = 0; i < u.getFavoriteIds().size(); i++) {
                    fBuilder.append(u.getFavoriteIds().get(i));
                    if (i < u.getFavoriteIds().size() - 1) fBuilder.append(";");
                }

                bw.write(u.getId() + "," + u.getUsername() + "," + wBuilder.toString() + "," + fBuilder.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MyLinkedList<WatchHistoryItem> loadWatchHistory() {
        MyLinkedList<WatchHistoryItem> list = new MyLinkedList<>();
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

    public static void saveWatchHistory(MyLinkedList<WatchHistoryItem> list) {
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