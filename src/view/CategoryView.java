package view;

import controller.CategoryController;
import java.util.Scanner;
import model.Category;
import util.MyLinkedList;

public class CategoryView {

    private CategoryController categoryController;
    private Scanner scanner;

    public CategoryView(CategoryController categoryController, Scanner scanner) {
        this.categoryController = categoryController;
        this.scanner = scanner;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=== CATEGORY MANAGEMENT ===");
            System.out.println("1. View All Categories");
            System.out.println("2. Add New Category");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    showAllCategories();
                    break;
                case "2":
                    addNewCategory();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    public void showAllCategories() {
        MyLinkedList<Category> list = categoryController.getAllCategories();
        if (list.isEmpty()) {
            System.out.println("No categories found.");
            return;
        }
        System.out.println("\n--- CATEGORY LIST ---");
        for (Category c : list) {
            System.out.println(c.toString());
        }
    }

    private void addNewCategory() {
        System.out.print("Enter Category ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Category Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Category Description: ");
        String desc = scanner.nextLine().trim();

        Category category = new Category(id, name, desc);
        if (categoryController.addCategory(category)) {
            System.out.println("Category added successfully!");
        } else {
            System.out.println("Failed to add category (Duplicate ID or invalid data)!");
        }
    }
}