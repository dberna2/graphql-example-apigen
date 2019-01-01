package com.graphql.springbootexample.service.book;

import com.graphql.schema.library.book.Book;
import graphql.GraphQLException;

import java.util.List;

public interface IBookService {

    Book findById(String id);

    List<Book> findAll();

    Book create(Book book);

    Book update(Book book);

    Boolean delete(String id)throws GraphQLException;
}
