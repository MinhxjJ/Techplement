package com.quizApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizOperations {
    private SessionFactory factory;
    private Scanner scanner = new Scanner(System.in);

    public QuizOperations() {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(QuizQuestions.class).buildSessionFactory();
    }

    public void addQuestion() {
        System.out.print("Enter question: ");
        String question = scanner.nextLine();

        List<String> options = new ArrayList<>(); // ✅ Mutable List
        for (int i = 1; i <= 4; i++) {
            System.out.print("Enter option " + i + ": ");
            options.add(scanner.nextLine()); // ✅ Correct way to add elements
        }

        System.out.print("Enter correct answer (1-4): ");
        int correctAnswer = scanner.nextInt();
        scanner.nextLine();

        QuizQuestions newQuestion = new QuizQuestions(question, options, correctAnswer);

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(newQuestion);
        tx.commit();
        session.close();

        System.out.println("Question added successfully!\n");
    }


    public void takeQuiz() {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            List<QuizQuestions> questions = session.createQuery("from QuizQuestions", QuizQuestions.class).getResultList();
            tx.commit();

            if (questions.isEmpty()) {
                System.out.println("No questions available. Add some first!");
                return;
            }

            int score = 0;
            for (int i = 0; i < questions.size(); i++) {
            	QuizQuestions q = questions.get(i);
                System.out.println("\nQ" + (i + 1) + ": " + q.getQuestion());
                for (int j = 0; j < q.getOptions().size(); j++) {
                    System.out.println((j + 1) + ". " + q.getOptions().get(j));
                }

                System.out.print("Your answer: ");
                int answer = scanner.nextInt();
                scanner.nextLine();

                if (answer == q.getCorrectAnswer()) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong! Correct answer: " + q.getCorrectAnswer());
                }
            }

            System.out.println("\nQuiz Finished!");
            System.out.println("Your Score: " + score + "/" + questions.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // **Method to Delete a Question**
    public void deleteQuestion() {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            List<QuizQuestions> questions = session.createQuery("from QuizQuestions", QuizQuestions.class).getResultList();
            tx.commit();

            if (questions.isEmpty()) {
                System.out.println("No questions available to delete.");
                return;
            }

            // Display all questions with their IDs
            System.out.println("\nAvailable Questions:");
            for (QuizQuestions q : questions) {
                System.out.println(q.getId() + ". " + q.getQuestion());
            }

            // Prompt user to enter question ID to delete
            System.out.print("\nEnter the ID of the question to delete: ");
            int questionId = scanner.nextInt();
            scanner.nextLine();

            // Find and delete the question
            Session deleteSession = factory.openSession();
            Transaction deleteTx = deleteSession.beginTransaction();

            QuizQuestions questionToDelete = deleteSession.get(QuizQuestions.class, questionId);
            if (questionToDelete != null) {
                deleteSession.delete(questionToDelete);
                System.out.println("Question deleted successfully!");
            } else {
                System.out.println("Question with ID " + questionId + " not found.");
            }

            deleteTx.commit();
            deleteSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
