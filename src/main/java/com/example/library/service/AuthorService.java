package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Author findByIdOrThrow(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found: " + id));
    }

    @Transactional
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public Author update(Long id, Author incoming) {
        Author existing = findByIdOrThrow(id);
        existing.setFullName(incoming.getFullName());
        existing.setEmail(incoming.getEmail());
        return authorRepository.save(existing);
    }
}
