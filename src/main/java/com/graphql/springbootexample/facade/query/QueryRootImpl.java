package com.graphql.springbootexample.facade.query;

import com.graphql.schema.library.book.Book;
import com.graphql.schema.library.root.query.QueryRoot;
import com.graphql.springbootexample.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class QueryRootImpl implements QueryRoot {

	@Autowired
    private IBookService bookService;

    @Override
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @Override
    public Book book(BookArgs args) {
        return new Book.Unresolved(args.getId());
    }

}
