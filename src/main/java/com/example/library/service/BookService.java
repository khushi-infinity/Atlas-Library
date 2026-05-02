package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * List books using repository custom inner-join query (assignment requirement).
     */
    @Transactional(readOnly = true)
    public List<Book> findAllWithAuthorsViaJoin() {
        return bookRepository.findAllBooksWithAuthorsInnerJoin();
    }

    @Transactional(readOnly = true)
    public Book findByIdOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + id));
    }

    @Transactional
    public Book create(Book book, Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found: " + authorId));
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @Transactional
    public Book update(Long id, Book incoming, Long authorId) {
        Book existing = findByIdOrThrow(id);
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found: " + authorId));
        existing.setTitle(incoming.getTitle());
        existing.setIsbn(incoming.getIsbn());
        existing.setAuthor(author);
        return bookRepository.save(existing);
    }
}
