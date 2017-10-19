package server.Controllers;
import com.google.gson.Gson;
import server.DBWrapper;
import server.endpoints.QuestionEndpoint;
import server.endpoints.QuizEndpoint;
import server.models.Question;
import server.models.Quiz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionController {
    Gson gson;
    DBWrapper db = new DBWrapper ();


    public QuestionController(){
        this.gson = new Gson();
    }



    public ArrayList <Question> getQuestions (int quiz) throws IOException, ClassNotFoundException {
        ArrayList<Question> q = db.getQuestions(quiz);
        return q;


    }

    public Question createQuestion(String jsonQuestion) throws Exception {
        Question newQuestion = new Gson().fromJson(jsonQuestion, Question.class);
        db.createQuestion(newQuestion);
        return newQuestion;
    }
}
