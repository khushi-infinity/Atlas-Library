package com.example.library.config;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class SampleDataLoader {

    @Bean
    @Order(1)
    ApplicationRunner loadSampleLibrary(AuthorRepository authors, BookRepository books) {
        return args -> {
            if (authors.count() > 0) {
                return;
            }

            Author[] sampleAuthors = new Author[] {
                    author("Jane Austen", "jane.austen@example.com"),
                    author("George Orwell", "george.orwell@example.com"),
                    author("Toni Morrison", "toni.morrison@example.com"),
                    author("Chinua Achebe", "chinua.achebe@example.com"),
                    author("Haruki Murakami", "haruki.murakami@example.com"),
                    author("Arundhati Roy", "arundhati.roy@example.com"),
                    author("Gabriel García Márquez", "gabo@example.com"),
                    author("Octavia E. Butler", "octavia.butler@example.com"),
                    author("Jhumpa Lahiri", "jhumpa.lahiri@example.com"),
                    author("Isaac Asimov", "isaac.asimov@example.com")
            };

            java.util.List<Author> authorList = new java.util.ArrayList<>();
            for (Author a : sampleAuthors) {
                authorList.add(authors.save(a));
            }

            Book[] sampleBooks = new Book[] {
                    book("Pride and Prejudice", "ISBN-1001", authorList.get(0)),
                    book("1984", "ISBN-1002", authorList.get(1)),
                    book("Beloved", "ISBN-1003", authorList.get(2)),
                    book("Things Fall Apart", "ISBN-1004", authorList.get(3)),
                    book("Kafka on the Shore", "ISBN-1005", authorList.get(4)),
                    book("The God of Small Things", "ISBN-1006", authorList.get(5)),
                    book("One Hundred Years of Solitude", "ISBN-1007", authorList.get(6)),
                    book("Kindred", "ISBN-1008", authorList.get(7)),
                    book("Interpreter of Maladies", "ISBN-1009", authorList.get(8)),
                    book("Foundation", "ISBN-1010", authorList.get(9))
            };

            for (Book b : sampleBooks) {
                books.save(b);
            }
        };
    }

    private static Author author(String name, String email) {
        Author a = new Author();
        a.setFullName(name);
        a.setEmail(email);
        return a;
    }

    private static Book book(String title, String isbn, Author author) {
        Book b = new Book();
        b.setTitle(title);
        b.setIsbn(isbn);
        b.setAuthor(author);
        return b;
    }
}
