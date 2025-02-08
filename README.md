Quiz Generator Application

Introduction : 
The Quiz Generator is a command-line application that allows users to create quizzes, add multiple-choice questions, specify correct answers, and take quizzes. The system stores questions and answers using Hibernate with MySQL.

Features : 
- Add multiple-choice questions
- Take quizzes with scoring and feedback
- Delete existing questions
- Persistent storage using MySQL and Hibernate
- 
Requirements : 
- Java 8 or later
- MySQL installed and running
- Hibernate
- 
Database Setup : 
1. Open MySQL and create the database:
 CREATE DATABASE quizdb;
2. Ensure the MySQL username and password matches with your usernaem and password in hibernate.cfg.xml file

Project Structure : 
Create the com.quizApp folder inside your Java project and place the files accordingly.

Commands Usage : 
- Add Question: Allows the user to add a new question with four options and specify the correct answer.
- Take Quiz: Displays questions, collects answers, and shows the final score.
- Delete Question: Lists all questions and allows the user to delete one by ID.
- Exit: Closes the application.

Note : 
- Make sure MySQL is running before starting the application.
- If you encounter errors related to Hibernate, check database connectivity and dependencies.
