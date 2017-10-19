package server.Controllers;
import com.google.gson.Gson;
import server.DBWrapper;
import server.endpoints.QuestionEndpoint;
import server.endpoints.QuizEndpoint;
import server.models.Question;
import server.models.Quiz;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionController {
    Gson gson;
    DBWrapper db = new DBWrapper ();


    public QuestionController(){
        this.gson = new Gson();
    }



    public ArrayList <Question> getQuestions (Quiz quiz){
        ArrayList<Question> q = db.getQuestions(quiz);
        return q;


    }

    public void createQuestion(Question question) throws SQLException {
        Question newQuestion = new Gson().fromJson(question, Question.class);
        db.createQuestion(newQuestion);
    }
}
