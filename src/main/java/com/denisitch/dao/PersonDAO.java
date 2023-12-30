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
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Person",
                new BeanPropertyRowMapper<>(Person.class)
        );
    }

    public Person show(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Person WHERE id=?",
                new BeanPropertyRowMapper<>(Person.class),
                id
        ).stream().findAny().orElse(null);
    }

    public Optional<Person> show(String fullName) {
        return jdbcTemplate.query(
                "SELECT * FROM person WHERE fullname=?",
                new BeanPropertyRowMapper<>(Person.class),
                fullName
        ).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update(
                "INSERT INTO Person(fullname, yearofbirth) VALUES(?, ?)",
                person.getFullName(),
                person.getYearOfBirth()
        );
    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update(
                "UPDATE Person SET fullname=?, yearofbirth=? WHERE id=?",
                updatePerson.getFullName(),
                updatePerson.getYearOfBirth(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update(
                "DELETE FROM Person WHERE id=?",
                id
        );
    }

    public List<Book> showBooks(int person_id) {
        return jdbcTemplate.query(
                "SELECT * FROM book WHERE person_id=?",
                new BeanPropertyRowMapper<>(Book.class),
                person_id
        );
    }
}
