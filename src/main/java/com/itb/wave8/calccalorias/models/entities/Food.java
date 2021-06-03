package com.itb.wave8.calccalorias.models.entities;

import java.util.Objects;

public class Food {

    private String name;
    private Integer calories;

    public Food() {
    }

    public Food(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(name, food.name) && Objects.equals(calories, food.calories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, calories);
    }

    @Override
    public String toString() {
        return "Nombre: " + name + " calorias: " + calories;
    }
}
