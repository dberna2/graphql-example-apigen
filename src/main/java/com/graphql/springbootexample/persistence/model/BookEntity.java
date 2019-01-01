package com.graphql.springbootexample.persistence.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table
@Entity(name = "Book")
public class BookEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String isn;
    private String title;
    private Date publishedDate;

    public BookEntity(String isn, String title, Date publishedDate) {
        this.isn = isn;
        this.title = title;
        this.publishedDate = publishedDate;
    }
}
