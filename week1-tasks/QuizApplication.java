package com.quizApp;

import java.util.Scanner;

public class QuizApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuizOperations quiz = new QuizOperations();

        while (true) {
            System.out.println("\nQuiz Generator");
            System.out.println("1. Add Question");
            System.out.println("2. Take Quiz");
            System.out.println("3. Delete Question");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    quiz.addQuestion();
                    break;
                case 2:
                    quiz.takeQuiz();
                    break;
                case 3:
                    quiz.deleteQuestion();
                    break;
                case 4:
                    System.out.println("Exiting Quiz Generator. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
