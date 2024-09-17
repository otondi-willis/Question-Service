package com.willis.question_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //other fields
    private String category;
    private String difficultylevel;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String questiontitle;
    private String rightanswer;

}
