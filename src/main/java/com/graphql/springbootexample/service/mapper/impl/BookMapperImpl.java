package com.graphql.springbootexample.service.mapper.impl;

import com.graphql.schema.library.book.Book;
import com.graphql.springbootexample.persistence.model.BookEntity;
import com.graphql.springbootexample.service.mapper.IBookMapper;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class BookMapperImpl implements IBookMapper{

    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Book bookEntityToBook(BookEntity book) {
        return new Book.Builder()
                .withId(book.getId())
                .withIsn(book.getIsn())
                .withTitle(book.getTitle())
                .withPublishedDate(buildDateToString(book.getPublishedDate()))
        .build();

    }

    @Override
    public BookEntity bookToBookEntity(Book book) {
        return BookEntity.builder()
                .id(book.getId())
                .isn(book.getIsn())
                .title(book.getTitle())
                .publishedDate(buildStringToDate(book.getPublishedDate()))
        .build();
    }

    private Date buildStringToDate(String value){
        try {
            return format.parse(value);
        } catch (Exception e) {
            return null;
        }
    }

    private String buildDateToString(Date value){
        try {
            return format.format(value);
        } catch (Exception e) {
            return null;
        }
    }
}
