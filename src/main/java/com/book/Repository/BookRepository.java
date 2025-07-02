package com.book.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.Model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByTitle(String title);

    List<Book> findByAuthor_NameContainingIgnoreCase(String name);
}
