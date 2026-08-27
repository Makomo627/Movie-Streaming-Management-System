package model;

public class Movie {
    private String id;
    private String title;
    private String director;
    private String actor;
    private String genre;
    private int releaseYear;
    private double rating;
    private int views;
    private int durationInSeconds;

    public Movie(String id, String title, String director, String actor, String genre, int releaseYear, double rating, int views, int durationInSeconds) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.actor = actor;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.views = views;
        this.durationInSeconds = durationInSeconds;
    }

    public String toFileString(){
        return id + "," + title + "," + director + "," + actor + "," + genre + "," + releaseYear + "," + rating + "," + views + "," + durationInSeconds;
    }

    public static Movie fromFileString(String line){
        String[] p = line.split(",");
        if (p.length < 9) return null;
        return new Movie(p[0], p[1], p[2], p[3], p[4], Integer.parseInt(p[5]), Double.parseDouble(p[6]), Integer.parseInt(p[7]), Integer.parseInt(p[8]));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Director: %s | Actor: %s | Genre: %s | Release Year: %d | Rating: %.1f | Views: %d",
                id, title, director, actor, genre, releaseYear, rating, views);
    }
    
    public String getId(){ 
        return id; 
    }

    public String getTitle(){ 
        return title; 
    }

    public String getDirector(){ 
        return director; 
    }

    public String getActor(){ 
        return actor; 
    }

    public String getGenre(){ 
        return genre;
    }

    public int getReleaseYear(){ 
        return releaseYear; 
    }

    public double getRating(){ 
        return rating; 
    }

    public int getViews(){ 
        return views; 
    }

    public int getDurationInSeconds(){ 
        return durationInSeconds; 
    }

    public void incrementViews(){ 
        this.views++; 
    }

}