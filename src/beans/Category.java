package beans;

import Utils.RandomFirstName;

import java.util.Random;

public enum Category {
    FOOD, ELECTRICITY, RESTAURANT,VACATION, FASHION, CINEMA, SPA, TECH, SPORT;


    public static String getRandomCategory() {
        Random random = new Random();
        Category[] categories = values();
        return categories[random.nextInt(categories.length)].toString();
    }
}



