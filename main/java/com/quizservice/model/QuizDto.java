package com.quizservice.model;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    private Integer numQ;
    private String quizTitle;
}
