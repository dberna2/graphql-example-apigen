package com.graphql.springbootexample.facade.resolver.book.mutation;

import com.graphql.schema.library.book.Book;
import com.graphql.schema.library.mutation.Mutation;
import com.graphql.springbootexample.service.book.IBookService;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMutationImpl implements Mutation {

    @Autowired
    private IBookService bookService;

    @Override
    public Book createBook(CreateBookArgs args) {
        Book book = new Book.Builder()
                .withIsn(args.getBook().getIsn())
                .withTitle(args.getBook().getTitle())
                .withPublishedDate(args.getBook().getPublishedDate()).build();
        return bookService.create(book);
    }

    @Override
    public Boolean deleteBook(DeleteBookArgs args) throws GraphQLException {
        return bookService.delete(args.getId());
    }

    @Override
    public Book updateBook(UpdateBookArgs args) {

        Book book = new Book.Builder()
                .withIsn(args.getBook().getIsn())
                .withTitle(args.getBook().getTitle())
                .withPublishedDate(args.getBook().getPublishedDate()).build();

        return bookService.update(book);
    }
}
