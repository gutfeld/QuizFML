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
    Log log = new Log();


    public QuestionController(){
        this.gson = new Gson();
    }



    public ArrayList <Question> getQuestions (int quiz) throws IOException, ClassNotFoundException {
        log.writeLog(this.getClass().getName(), this, "We are now getting questions", 2);
        ArrayList<Question> q = db.getQuestions(quiz);
        return q;


    }

    public Question createQuestion(String jsonQuestion) throws Exception {
        Question newQuestion = new Gson().fromJson(jsonQuestion, Question.class);
        Question isCreated = db.createQuestion(newQuestion);
        return isCreated;
    }
}
