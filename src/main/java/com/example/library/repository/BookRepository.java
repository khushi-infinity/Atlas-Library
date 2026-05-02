package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Custom query: inner join Book with Author (only books that have an author row).
     * FETCH initializes the author association for use in the view layer.
     */
    @Query("SELECT DISTINCT b FROM Book b INNER JOIN FETCH b.author a ORDER BY b.id ASC")
    List<Book> findAllBooksWithAuthorsInnerJoin();
}
