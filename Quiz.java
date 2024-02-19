package p1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public Question(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

public class Quiz{
    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static int questionIndex = 0;
    private static final int TIME_LIMIT = 10; // Time limit per question in seconds

    public static void main(String[] args) {
        prepareQuestions();

        System.out.println("Welcome to the Quiz Game!");
        System.out.println("You will have " + TIME_LIMIT + " seconds to answer each question.");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                displayNextQuestion();
            }
        }, TIME_LIMIT * 1000); // Convert seconds to milliseconds

        displayNextQuestion();
    }

    private static void displayNextQuestion() {
        if (questionIndex < questions.size()) {
            Question currentQuestion = questions.get(questionIndex++);
            System.out.println("\n" + currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ". " + options.get(i));
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            int userChoice = scanner.nextInt();

            if (userChoice == currentQuestion.getCorrectOptionIndex() + 1) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }

            displayNextQuestion();
        } else {
            System.out.println("\nQuiz Over!");
            System.out.println("Your Score: " + score + "/" + questions.size());
        }
    }

    private static void prepareQuestions() {
        // Add your questions here along with multiple-choice options and correct answer index
        questions.add(new Question("What is the capital of France?", List.of("London", "Paris", "Berlin", "Rome"), 1));
        questions.add(new Question("Which planet is known as the Red Planet?", List.of("Mars", "Jupiter", "Venus", "Mercury"), 0));
        questions.add(new Question("Who wrote 'Romeo and Juliet'?", List.of("William Shakespeare", "Charles Dickens", "Jane Austen", "Leo Tolstoy"), 0));
        // Add more questions as needed
    }
}

