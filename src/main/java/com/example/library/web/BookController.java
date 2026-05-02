package com.example.library.web;

import com.example.library.entity.Book;
import com.example.library.service.AuthorService;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    /**
     * Read: list is populated from the custom inner-join repository method.
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookService.findAllWithAuthorsViaJoin());
        return "books/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "books/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("book") Book book,
                         BindingResult bindingResult,
                         @RequestParam("authorId") Long authorId,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        model.addAttribute("authors", authorService.findAll());
        if (authorId == null) {
            bindingResult.reject("authorId", "Please select an author.");
        }
        if (bindingResult.hasErrors()) {
            return "books/form";
        }
        try {
            bookService.create(book, authorId);
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Could not create book: duplicate ISBN or invalid author.");
            return "redirect:/books/new";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Book created successfully.");
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findByIdOrThrow(id));
        model.addAttribute("authors", authorService.findAll());
        return "books/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("book") Book book,
                         BindingResult bindingResult,
                         @RequestParam("authorId") Long authorId,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        model.addAttribute("authors", authorService.findAll());
        if (authorId == null) {
            bindingResult.reject("authorId", "Please select an author.");
        }
        if (bindingResult.hasErrors()) {
            return "books/form";
        }
        try {
            bookService.update(id, book, authorId);
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Could not update book: duplicate ISBN or invalid author.");
            return "redirect:/books/" + id + "/edit";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Book updated successfully.");
        return "redirect:/books";
    }
}
