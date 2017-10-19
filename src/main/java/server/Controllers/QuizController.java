package server.Controllers;


import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Quiz;

public class QuizController {

    Gson gson;
    DBWrapper db = new DBWrapper();

    public QuizController() {
        this.gson = gson;
    }

    public void createQuiz (String quiz) throws Exception {

        Quiz newQuiz = new Gson().fromJson(quiz, Quiz.class);

        db.createQuiz(newQuiz);
    }
}