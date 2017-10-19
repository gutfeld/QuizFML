package server.Controllers;


import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Course;
import server.models.Quiz;
import server.models.User;

import java.util.ArrayList;

public class QuizController {

    Gson gson;
    DBWrapper db = new DBWrapper();
    ArrayList<Quiz> quizzes;

    public QuizController() {
        this.gson = gson;
    }

    public void createQuiz (String quiz) throws Exception {

        Quiz newQuiz = new Gson().fromJson(quiz, Quiz.class);

        db.createQuiz(newQuiz);
    }

   /* public ArrayList<Quiz> getQuizzes(Course course) {

        ArrayList<Quiz> quizzes = db.getQuizzes(course);
        for (int i = 0; i < quizzes.size(); i++) System.out.println(quizzes.get(i));
        return quizzes;
    }

    public Quiz findByCourseId(int courseId) {

        for (Quiz quiz : this.quizzes) {

            if (quiz.getCourseID() == courseId) {

                return quiz;
            }

        }

            return null;
    }

*/
}