package com.denisitch.dao;

import com.denisitch.models.Book;
import com.denisitch.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class)
        );
    }

    public Book show(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE id=?",
                new BeanPropertyRowMapper<>(Book.class),
                id
        ).stream().findAny().orElse(null);
    }

    public Optional<Person> getPersonOfBook(int id) {
        return jdbcTemplate.query(
                "SELECT p.* FROM book b JOIN person p on p.id = b.person_id WHERE b.id=?",
                new BeanPropertyRowMapper<>(Person.class),
                id
        ).stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO Book(title, author, year) VALUES (?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear()
        );
    }

    public void update(int id, Book updateBook) {
        jdbcTemplate.update(
                "UPDATE Book SET title=?, author=?, year=? WHERE id=?",
                updateBook.getTitle(),
                updateBook.getAuthor(),
                updateBook.getYear(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM Book WHERE id=?",
                id
        );
    }

    public void assignBook(int person_id, int book_id) {
        jdbcTemplate.update(
                "UPDATE Book SET person_id=? WHERE id=?",
                person_id,
                book_id
        );
    }

    public void unassignBook(int book_id) {
        jdbcTemplate.update(
                "UPDATE Book SET person_id=NULL WHERE id=?",
                book_id
        );
    }
}
