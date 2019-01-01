package com.graphql.springbootexample.persistence.repository;

import com.graphql.springbootexample.persistence.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, String>{
}
