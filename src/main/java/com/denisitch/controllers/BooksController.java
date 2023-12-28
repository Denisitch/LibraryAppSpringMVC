package com.denisitch.controllers;

import com.denisitch.dao.BooksDAO;
import com.denisitch.dao.PersonDAO;
import com.denisitch.models.Book;
import com.denisitch.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("people", personDAO.index());
        return "books/show";
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

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book) {
        booksDAO.save(book);
        return "redirect:/books";
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
            @PathVariable("id") int id
    ) {
        booksDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksDAO.delete(id);
        return "redirect:/books";
    }
}
