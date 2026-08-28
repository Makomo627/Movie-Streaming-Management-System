package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Movie;

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
}