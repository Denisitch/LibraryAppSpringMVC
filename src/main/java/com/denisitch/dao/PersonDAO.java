package com.denisitch.dao;

import com.denisitch.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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

}