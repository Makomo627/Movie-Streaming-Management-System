package controller;

import model.Movie;
import storage.FileStorage;
import util.MyLinkedList;

public class MovieController {
    private MyLinkedList<Movie> movies;

    public MovieController() {
        this.movies = FileStorage.loadMovies();
    }

    public MyLinkedList<Movie> getAllMovies() {
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

    public MyLinkedList<Movie> searchByTitle(String keyword) {
        MyLinkedList<Movie> results = new MyLinkedList<>();
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

    public MyLinkedList<Movie> filterByGenre(String genre) {
        MyLinkedList<Movie> results = new MyLinkedList<>();
        if (genre == null) return results;
        for (Movie m : movies) {
            if (m.getGenre() != null && m.getGenre().equalsIgnoreCase(genre.trim())) {
                results.add(m);
            }
        }
        return results;
    }
}