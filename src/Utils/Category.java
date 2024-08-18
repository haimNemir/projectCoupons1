package Utils;

import java.util.HashMap;
import java.util.Random;
// you need entered manually Enum variables to the DB from this Enum!!
public enum Category {
    FOOD, ELECTRICITY, RESTAURANT, VACATION, FASHION, CINEMA, SPA, TECH, SPORT;


    public static String getRandomCategoryByString() {
        Random random = new Random();
        Category[] categories = values();
        return categories[random.nextInt(categories.length)].toString();
    }

    /**
     *
     * @param category Enum,
     * @return category_id how it saved in the DB
     */
    public static int getIdFromCategory(Category category) {
        HashMap<Category, Integer> categories = new HashMap<>();
        categories.put(FOOD, 1);
        categories.put(ELECTRICITY, 2);
        categories.put(RESTAURANT, 3);
        categories.put(VACATION, 4);
        categories.put(FASHION, 5);
        categories.put(CINEMA, 6);
        categories.put(SPA, 7);
        categories.put(TECH, 8);
        categories.put(SPORT, 9);
        return categories.get(category);
    }

    /**
     *
     * @param category_id  how it's saved in the DB
     * @return Category Enum.
     */
    public static Category getCategoryFromID(int category_id) {
        HashMap<Integer, Category > categories = new HashMap<>();
        categories.put(1, FOOD);
        categories.put(2, ELECTRICITY);
        categories.put(3, RESTAURANT);
        categories.put(4, VACATION);
        categories.put(5, FASHION);
        categories.put(6, CINEMA);
        categories.put(7, SPA);
        categories.put(8, TECH);
        categories.put(9, SPORT);
        return categories.get(category_id);
    }
}



