package com.denisitch.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class Person {
    private int id;
    @NotEmpty(message = "Поле 'ФИО' не должно быть пустым")
    @Size(min = 7, max = 100, message = "ФИО должно быть от 7 до 100 символов")
    private String fullName;
    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Max(value = 2024, message = "Год рождения должен быть больше 2024")
    private int yearOfBirth;

    public Person(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
