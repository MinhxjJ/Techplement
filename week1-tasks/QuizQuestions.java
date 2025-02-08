package com.quizApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "questions")
public class QuizQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    
    @ElementCollection(fetch = FetchType.EAGER) // Change here
    private List<String> options;
    
    private int correctAnswer;

    public QuizQuestions() {}

    public QuizQuestions(String question, List<String> options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
    
    public int getId() {
        return id;
    }

}