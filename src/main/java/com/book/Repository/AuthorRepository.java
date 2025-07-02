package com.book.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.Model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{

    boolean existsByName(String name);

    Optional<Author> findByName(String name);

}
