package model;

public class WatchHistoryItem {
    private String userId;
    private String movieId;
    private String watchedAt;
    private int stoppedAtSeconds;

    public WatchHistoryItem(String userId, String movieId, String watchedAt, int stoppedAtSeconds) {
        this.userId = userId;
        this.movieId = movieId;
        this.watchedAt = watchedAt;
        this.stoppedAtSeconds = stoppedAtSeconds;
    }

    public String toFileString() {
        return userId + "," + movieId + "," + watchedAt + "," + stoppedAtSeconds;
    }

    public static WatchHistoryItem fromFileString(String line) {
        String[] p = line.split(",");
        if (p.length < 4) return null;
        return new WatchHistoryItem(p[0], p[1], p[2], Integer.parseInt(p[3]));
    }

    public String getUserId(){ 
        return userId; 
    }

    public String getMovieId(){ 
        return movieId; 
    }

    public int getStoppedAtSeconds(){ 
        return stoppedAtSeconds; 
    }

    public String getWatchedAt(){ 
        return watchedAt; 
    }
    
}