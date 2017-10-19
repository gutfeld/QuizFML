package server.Controllers;


import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Quiz;
import java.util.ArrayList;

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

    public ArrayList<Quiz> getQuizzes(int courseId) {

        ArrayList<Quiz> allQuizzes = db.getQuizzes(courseId);
        // for (int i = 0; i < allQuizzes.size(); i++) System.out.println(allQuizzes.get(i));
        return allQuizzes;
    }
}