package model;

import util.MyLinkedList;
import util.MyQueue;
import util.MyStack;

public class User {
    private String id;
    private String username;
    private MyLinkedList<String> watchlistIds = new MyLinkedList<>();
    private MyLinkedList<String> favoriteIds = new MyLinkedList<>();
    private MyQueue<String> playbackQueue = new MyQueue<>();
    private MyStack<String> recentActionStack = new MyStack<>();

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

    public MyLinkedList<String> getWatchlistIds() { 
        return watchlistIds; 
    }

    public MyLinkedList<String> getFavoriteIds() { 
        return favoriteIds; 
    }

    public MyQueue<String> getPlaybackQueue() {
        return playbackQueue;
    }

    public MyStack<String> getRecentActionStack() {
        return recentActionStack;
    }
}