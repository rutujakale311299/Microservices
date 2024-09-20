package com.quizservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    private Integer id;

    @ElementCollection                                                       //we are storing collection of Ids that's why use
    private List<Integer> questionIds;
    private String Title;
}
