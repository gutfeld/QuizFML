package server.Controllers;


import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Course;
import server.models.Quiz;
import server.models.User;

import java.io.IOException;
import java.util.ArrayList;

public class QuizController {

    Gson gson;
    DBWrapper db = new DBWrapper();
    ArrayList<Quiz> quizzes;

    public QuizController() {
        this.gson = gson;
    }

    public Quiz createQuiz (String quiz) throws Exception {

        Quiz newQuiz = new Gson().fromJson(quiz, Quiz.class);

        db.createQuiz(newQuiz);
        return newQuiz;
    }

    public ArrayList<Quiz> getQuizzes(int courseId) throws IOException, ClassNotFoundException {
        ArrayList<Quiz> allQuizzes = db.getQuizzes(courseId);
        return allQuizzes;
    }

    public Boolean deleteQuiz (int quizId) throws Exception {
       return db.deleteQuiz(quizId);
    }
}