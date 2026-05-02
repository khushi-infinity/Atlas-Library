package com.example.library.repository;

import com.example.library.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findByEmailIgnoreCase_returnsAuthor() {
        Author a = new Author();
        a.setFullName("Email Lookup");
        a.setEmail("Mixed.Case@Example.com");
        entityManager.persist(a);
        entityManager.flush();

        Optional<Author> found = authorRepository.findByEmailIgnoreCase("mixed.case@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getFullName()).isEqualTo("Email Lookup");
    }
}
