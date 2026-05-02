package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void update_appliesChangesAndSaves() {
        Author existing = new Author();
        existing.setId(3L);
        existing.setFullName("Old");
        existing.setEmail("old@example.com");
        when(authorRepository.findById(3L)).thenReturn(Optional.of(existing));
        when(authorRepository.save(existing)).thenReturn(existing);

        Author incoming = new Author();
        incoming.setFullName("New Name");
        incoming.setEmail("new@example.com");

        Author result = authorService.update(3L, incoming);

        assertThat(result.getFullName()).isEqualTo("New Name");
        assertThat(result.getEmail()).isEqualTo("new@example.com");
        verify(authorRepository).save(existing);
    }

    @Test
    void findByIdOrThrow_whenMissing() {
        when(authorRepository.findById(9L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.findByIdOrThrow(9L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Author not found");
    }

    @Test
    void create_savesEntity() {
        Author a = new Author();
        when(authorRepository.save(any(Author.class))).thenAnswer(i -> i.getArgument(0));

        Author saved = authorService.create(a);

        assertThat(saved).isSameAs(a);
        verify(authorRepository).save(a);
    }
}
