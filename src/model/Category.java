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
        if (line == null || line.trim().isEmpty()) return null;
        String[] p = line.split(",");
        if (p.length < 3) return null;
        return new Category(p[0].trim(), p[1].trim(), p[2].trim());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s", id, name, description);
    }

    public String getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}