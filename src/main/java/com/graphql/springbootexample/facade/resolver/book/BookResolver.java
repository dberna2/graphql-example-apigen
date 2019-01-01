package com.graphql.springbootexample.facade.resolver.book;


import com.graphql.schema.library.book.Book;
import com.graphql.springbootexample.facade.resolver.BaseResolver;
import com.graphql.springbootexample.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookResolver extends BaseResolver<Book> implements Book.Resolver {

    @Autowired
    private IBookService bookService;


    @Override
    protected Book findById(Book book) {

        String bookId = book.getId();

        return bookService.findById(bookId);
    }

    @Override
    protected Class<?> unresolvedClass() {
        return Book.Unresolved.class;
    }
}
