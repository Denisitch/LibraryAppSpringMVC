package com.denisitch.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Поле 'Название книги' не должно быть пустым")
    @Size(min = 7, max = 100, message = "Название книги должно быть от 2 до 100 символов")
    private String title;
    @NotEmpty(message = "Поле 'Автор книги' не должно быть пустым")
    @Size(min = 7, max = 100, message = "Имя автора должно быть от 5 до 100 символов")
    private String author;
    @Min(value = 0, message = "Год выхода книги должен быть больше нуля")
    @Max(value = 2024, message = "Год выхода книги должен быть меньше 2024")
    private int year;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
