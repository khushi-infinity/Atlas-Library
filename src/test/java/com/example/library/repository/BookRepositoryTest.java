package com.example.library.repository;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAllBooksWithAuthorsInnerJoin_fetchesAuthorData() {
        Author a1 = new Author();
        a1.setFullName("Test Author");
        a1.setEmail("test.author.repo@example.com");
        entityManager.persist(a1);

        Book b1 = new Book();
        b1.setTitle("Joined Title");
        b1.setIsbn("ISBN-REPO-1");
        b1.setAuthor(a1);
        entityManager.persist(b1);
        entityManager.flush();
        entityManager.clear();

        List<Book> result = bookRepository.findAllBooksWithAuthorsInnerJoin();

        assertThat(result).hasSize(1);
        Book loaded = result.get(0);
        assertThat(loaded.getTitle()).isEqualTo("Joined Title");
        assertThat(loaded.getAuthor()).isNotNull();
        assertThat(loaded.getAuthor().getFullName()).isEqualTo("Test Author");
    }
}
