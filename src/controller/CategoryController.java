package controller;

import model.Category;
import storage.FileStorage;
import util.MyLinkedList;

public class CategoryController {
    private MyLinkedList<Category> categories;

    public CategoryController() {
        this.categories = FileStorage.loadCategories();
    }

    public MyLinkedList<Category> getAllCategories() {
        return categories;
    }

    public Category findById(String id) {
        if (id == null) return null;
        for (Category c : categories) {
            if (c.getId().equalsIgnoreCase(id.trim())) {
                return c;
            }
        }
        return null;
    }

    public boolean addCategory(Category category) {
        if (category == null || findById(category.getId()) != null) {
            return false;
        }
        categories.add(category);
        FileStorage.saveCategories(categories);
        return true;
    }

    public boolean updateCategory(String id, String newName) {
        Category c = findById(id);
        if (c != null) {
            c.setName(newName);
            FileStorage.saveCategories(categories);
            return true;
        }
        return false;
    }

    public boolean deleteCategory(String id) {
        Category c = findById(id);
        if (c != null) {
            categories.remove(c);
            FileStorage.saveCategories(categories);
            return true;
        }
        return false;
    }
}