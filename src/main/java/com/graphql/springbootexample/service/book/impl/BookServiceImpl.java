package com.graphql.springbootexample.service.book.impl;

import com.google.common.base.Preconditions;
import com.graphql.schema.library.book.Book;
import com.graphql.springbootexample.persistence.model.BookEntity;
import com.graphql.springbootexample.persistence.repository.BookRepository;
import com.graphql.springbootexample.service.book.IBookService;
import com.graphql.springbootexample.service.mapper.IBookMapper;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl  implements IBookService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private IBookMapper mapper;

    @Override
    public Book findById(String id) {

        Preconditions.checkNotNull(id, "Id can not be null");

        BookEntity book = repository.findOne(id);

        return mapper.bookEntityToBook(book);

    }

    @Override
    public List<Book> findAll() {

        List<BookEntity> allBooks = repository.findAll();

        List<Book> dtoList = allBooks.stream().map(mapper::bookEntityToBook)
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public Book create(Book book) {

        BookEntity bookEntity = mapper.bookToBookEntity(book);

        repository.save(bookEntity);

        return mapper.bookEntityToBook(bookEntity);
    }

    @Override
    public Book update(Book book) {

        BookEntity bookEntity = mapper.bookToBookEntity(book);

        repository.save(bookEntity);

        return mapper.bookEntityToBook(bookEntity);
    }

    @Override
    public Boolean delete(String id) throws GraphQLException{

        BookEntity book = repository.findOne(id);

        Preconditions.checkNotNull(book, "Book not found");

        repository.delete(book);

        return true;

    }
}
