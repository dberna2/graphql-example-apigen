package com.graphql.springbootexample.service.mapper;

import com.graphql.schema.library.book.Book;
import com.graphql.springbootexample.persistence.model.BookEntity;

public interface IBookMapper {

    Book bookEntityToBook(BookEntity book);

    BookEntity bookToBookEntity(Book book);

}
