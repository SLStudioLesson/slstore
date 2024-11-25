package com.example.slstore.practice.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.slstore.practice.model.Book;
import com.example.slstore.practice.model.LoginPerson;


@Controller
@RequestMapping("/practice")
@SessionAttributes("loginPerson")
public class SessionPracticeController {
    @ModelAttribute("loginPerson")
    public LoginPerson loginPerson() {
        LoginPerson loginPerson = new LoginPerson();
        loginPerson.setName("山田太郎");
        return loginPerson;
    }

    @GetMapping("/show/login")
    public String showLoginUser(LoginPerson loginPerson, Model model) {
        model.addAttribute("loginPerson", loginPerson);
        return "practice/display-login";
    }

    @GetMapping("/book-form")
    public String showBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "practice/book-form";
    }

    @PostMapping("/add/book")
    public String addBook(Book book, LoginPerson loginPerson) {
        List<Book> books = loginPerson.getBooks();
        books.add(book);
        return "redirect:/practice/show/login";
    }

    @GetMapping("/edit/book/{id}")
    public String showEditBookForm(@PathVariable("id") int id, LoginPerson loginPerson, Model model) {
        Book editBook = null;
        List<Book> books = loginPerson.getBooks();
        for (Book book : books) {
            if (book.getId() == id) {
                editBook = book;
                break;
            }
        }

        model.addAttribute("book", editBook);
        return "practice/edit-book-form";
    }

    @PostMapping("/edit/book")
    public String editBook(Book book, LoginPerson loginPerson) {
        List<Book> books = loginPerson.getBooks();
        for (Book nowBook : books) {
            if (nowBook.getId() == book.getId()) {
                nowBook.setTitle(book.getTitle());
                nowBook.setAuthor(book.getAuthor());
                break;
            }
        }
        return "redirect:/practice/show/login";
    }

    @GetMapping("/delete/book/{id}")
    public String deleteBook(@PathVariable("id") int id, LoginPerson loginPerson) {
        Book deleteBook = null;
        List<Book> books = loginPerson.getBooks();
        for (Book book : books) {
            if (book.getId() == id) {
                deleteBook = book;
                break;
            }
        }
        books.remove(deleteBook);
        return "redirect:/practice/show/login";
    }
}
