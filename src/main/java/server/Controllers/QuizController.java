package server.Controllers;


import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Course;
import server.models.Quiz;
import server.models.User;

import java.io.IOException;
import java.util.ArrayList;

public class QuizController {

    Log log = new Log();
    Gson gson;
    DBWrapper db = new DBWrapper();
    ArrayList<Quiz> quizzes;

    public QuizController() {
        this.gson = gson;
    }

    public void createQuiz (String quiz) throws Exception {

        log.writeLog(this.getClass().getName(), this, "We are now creating a quiz", 0);

        Quiz newQuiz = new Gson().fromJson(quiz, Quiz.class);

        db.createQuiz(newQuiz);
    }

    public ArrayList<Quiz> getQuizzes(int courseId) throws IOException, ClassNotFoundException {

        log.writeLog(this.getClass().getName(), this, "We are now getting quizzes", 0);
        ArrayList<Quiz> allQuizzes = db.getQuizzes(courseId);
        return allQuizzes;

    }

    public Boolean deleteQuiz (int quizId) throws Exception {
        log.writeLog(this.getClass().getName(), this, "We are deleting a quiz", 0);
       return db.deleteQuiz(quizId);
    }
}