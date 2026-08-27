package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String username;
    private List<String> watchlistIds = new ArrayList<>();
    private List<String> favoriteIds = new ArrayList<>();

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() { 
        return id; 
    }

    public String getUsername() { 
        return username; 
    }

    public List<String> getWatchlistIds() { 
        return watchlistIds; 
    }

    public List<String> getFavoriteIds() { 
        return favoriteIds; 
    }
}