package com.denisitch.controllers;

import com.denisitch.dao.BooksDAO;
import com.denisitch.dao.PersonDAO;
import com.denisitch.models.Book;
import com.denisitch.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksDAO booksDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, PersonDAO personDAO) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(
            @ModelAttribute("person") Person person,
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("book", booksDAO.show(id));

        Optional<Person> personOfBook = booksDAO.getPersonOfBook(id);
        if (personOfBook.isPresent()) {
            model.addAttribute("personOfBook", personOfBook.get());
        } else {
            model.addAttribute("people", personDAO.index());
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(
            @ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        } else {
            booksDAO.save(book);
            return "redirect:/books";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(
            @PathVariable("id") int id,
            Model model
    ) {
        model.addAttribute("book", booksDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(
            @ModelAttribute("book") Book book,
            BindingResult bindingResult,
            @PathVariable("id") int id
    ) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        } else {
            booksDAO.update(id, book);
            return "redirect:/books";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assignBook(
            @ModelAttribute("person") Person person,
            @PathVariable("id") int id
    ) {
        booksDAO.assignBook(person.getId(), id);
        return "redirect:/books/"+ id;
    }

    @PatchMapping("/{id}/unassign")
    public String unassignBook(
            @PathVariable("id") int id
    ) {
        booksDAO.unassignBook(id);
        return "redirect:/books/"+ id;
    }
}
