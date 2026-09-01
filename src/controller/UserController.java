package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.User;
import model.WatchHistoryItem;
import storage.FileStorage;
import util.MyLinkedList;

public class UserController {
    private MyLinkedList<User> users;
    private MyLinkedList<WatchHistoryItem> historyList;
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
            currentUser.getRecentActionStack().push("Added movie " + movieId + " to Watchlist");
            FileStorage.saveUsers(users);
            return true;
        }
        return false;
    }

    public boolean addToFavorites(String movieId) {
        if (currentUser == null || movieId == null) return false;
        if (!currentUser.getFavoriteIds().contains(movieId)) {
            currentUser.getFavoriteIds().add(movieId);
            currentUser.getRecentActionStack().push("Added movie " + movieId + " to Favorites");
            FileStorage.saveUsers(users);
            return true;
        }
        return false;
    }

    public boolean addToPlaybackQueue(String movieId) {
        if (currentUser == null || movieId == null) return false;
        currentUser.getPlaybackQueue().enqueue(movieId);
        currentUser.getRecentActionStack().push("Enqueued movie " + movieId + " to Playback Queue");
        return true;
    }

    public String playNextInQueue() {
        if (currentUser == null || currentUser.getPlaybackQueue().isEmpty()) {
            return null;
        }
        String movieId = currentUser.getPlaybackQueue().dequeue();
        currentUser.getRecentActionStack().push("Played movie " + movieId + " from Playback Queue");
        return movieId;
    }

    public String popLastAction() {
        if (currentUser == null || currentUser.getRecentActionStack().isEmpty()) {
            return null;
        }
        return currentUser.getRecentActionStack().pop();
    }

    public boolean recordWatchHistory(String movieId, int duration) {
        if (currentUser == null || movieId == null) return false;
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        WatchHistoryItem item = new WatchHistoryItem(currentUser.getId(), movieId, currentTime, duration);
        historyList.add(item);
        FileStorage.saveWatchHistory(historyList);
        return true;
    }

    public MyLinkedList<WatchHistoryItem> getCurrentUserHistory() {
        MyLinkedList<WatchHistoryItem> userHistory = new MyLinkedList<>();
        if (currentUser == null) return userHistory;

        for (WatchHistoryItem item : historyList) {
            if (item.getUserId().equalsIgnoreCase(currentUser.getId())) {
                userHistory.add(item);
            }
        }
        return userHistory;
    }
}