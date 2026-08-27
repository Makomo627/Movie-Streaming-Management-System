package model;

public class Category {
    private String id;
    private String name;
    private String description;

    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String toFileString() {
        return id + "," + name + "," + description;
    }

    public static Category fromFileString(String line) {
        String[] p = line.split(",");
        if (p.length < 3) return null;
        return new Category(p[0], p[1], p[2]);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", id, name, description);
    }

    public String getId(){ 
        return id; 
    }

    public String getName(){ 
        return name; 
    }
    
}