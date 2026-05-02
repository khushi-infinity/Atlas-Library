package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void findAllWithAuthorsViaJoin_delegatesToRepository() {
        Book b = new Book();
        when(bookRepository.findAllBooksWithAuthorsInnerJoin()).thenReturn(List.of(b));

        List<Book> books = bookService.findAllWithAuthorsViaJoin();

        assertThat(books).containsExactly(b);
        verify(bookRepository).findAllBooksWithAuthorsInnerJoin();
    }

    @Test
    void create_setsAuthorAndSaves() {
        Author author = new Author();
        author.setId(5L);
        when(authorRepository.findById(5L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> {
            Book arg = invocation.getArgument(0);
            arg.setId(99L);
            return arg;
        });

        Book incoming = new Book();
        incoming.setTitle("T");
        incoming.setIsbn("I-1");

        Book saved = bookService.create(incoming, 5L);

        assertThat(saved.getId()).isEqualTo(99L);
        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository).save(captor.capture());
        assertThat(captor.getValue().getAuthor()).isSameAs(author);
        assertThat(captor.getValue().getTitle()).isEqualTo("T");
    }

    @Test
    void update_throwsWhenBookMissing() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.update(1L, new Book(), 2L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Book not found");
    }
}
