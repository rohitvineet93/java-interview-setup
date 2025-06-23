package com.real.interview.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MovieDto {
    private Long id;
    private String title;
    private Integer releaseYear;
    private String updatedBy;
    private Timestamp createdAt;
}
