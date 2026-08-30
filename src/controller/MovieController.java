package controller;

import java.util.ArrayList;
import java.util.List;
import model.Movie;
import storage.FileStorage;

public class MovieController {
    private List<Movie> movies;

    public MovieController() {
        this.movies = FileStorage.loadMovies();
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public Movie findById(String id) {
        if (id == null) return null;
        for (Movie m : movies) {
            if (m.getId().equalsIgnoreCase(id.trim())) {
                return m;
            }
        }
        return null;
    }

    public boolean addMovie(Movie movie) {
        if (movie == null || findById(movie.getId()) != null) {
            return false;
        }
        movies.add(movie);
        FileStorage.saveMovies(movies);
        return true;
    }

    public boolean deleteMovie(String id) {
        Movie m = findById(id);
        if (m != null) {
            movies.remove(m);
            FileStorage.saveMovies(movies);
            return true;
        }
        return false;
    }

    public List<Movie> searchByTitle(String keyword) {
        List<Movie> results = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }
        String lowerKeyword = keyword.trim().toLowerCase();
        for (Movie m : movies) {
            if (m.getTitle().toLowerCase().contains(lowerKeyword)) {
                results.add(m);
            }
        }
        return results;
    }

    public List<Movie> filterByGenre(String genre) {
        List<Movie> results = new ArrayList<>();
        if (genre == null) return results;
        for (Movie m : movies) {
            if (m.getGenre() != null && m.getGenre().equalsIgnoreCase(genre.trim())) {
                results.add(m);
            }
        }
        return results;
    }
}