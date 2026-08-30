package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.WatchHistoryItem;
import storage.FileStorage;

public class UserController {
    private List<User> users;
    private List<WatchHistoryItem> historyList;
    private User currentUser;

    public UserController() {
        this.users = FileStorage.loadUsers();
        this.historyList = FileStorage.loadWatchHistory();
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public User findById(String id) {
        if (id == null) return null;
        for (User u : users) {
            if (u.getId().equalsIgnoreCase(id.trim())) {
                return u;
            }
        }
        return null;
    }

    public User findByUsername(String username) {
        if (username == null) return null;
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username.trim())) {
                return u;
            }
        }
        return null;
    }

    public boolean register(String id, String username) {
        if (findById(id) != null || findByUsername(username) != null) {
            return false;
        }
        User newUser = new User(id, username);
        users.add(newUser);
        FileStorage.saveUsers(users);
        return true;
    }

    public boolean login(String username) {
        User u = findByUsername(username);
        if (u != null) {
            this.currentUser = u;
            return true;
        }
        return false;
    }

    public void logout() {
        this.currentUser = null;
    }

    public boolean addToWatchlist(String movieId) {
        if (currentUser == null || movieId == null) return false;
        if (!currentUser.getWatchlistIds().contains(movieId)) {
            currentUser.getWatchlistIds().add(movieId);
            FileStorage.saveUsers(users);
            return true;
        }
        return false;
    }

    public boolean addToFavorites(String movieId) {
        if (currentUser == null || movieId == null) return false;
        if (!currentUser.getFavoriteIds().contains(movieId)) {
            currentUser.getFavoriteIds().add(movieId);
            FileStorage.saveUsers(users);
            return true;
        }
        return false;
    }

    public boolean recordWatchHistory(String movieId, int duration) {
        if (currentUser == null || movieId == null) return false;
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        WatchHistoryItem item = new WatchHistoryItem(currentUser.getId(), movieId, currentTime, duration);
        historyList.add(item);
        FileStorage.saveWatchHistory(historyList);
        return true;
    }

    public List<WatchHistoryItem> getCurrentUserHistory() {
        List<WatchHistoryItem> userHistory = new ArrayList<>();
        if (currentUser == null) return userHistory;

        for (WatchHistoryItem item : historyList) {
            if (item.getUserId().equalsIgnoreCase(currentUser.getId())) {
                userHistory.add(item);
            }
        }
        return userHistory;
    }
}