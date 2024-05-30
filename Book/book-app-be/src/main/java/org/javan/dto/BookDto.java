package org.javan.dto;

import lombok.Data;

@Data
public class BookDto {

    private String id;

    private String title;

    private String description;

    private float rating;

    private String author;

    private String isbn;

    private String createdAt;

    private String updatedAt;
}
