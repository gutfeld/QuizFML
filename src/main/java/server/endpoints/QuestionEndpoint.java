package server.endpoints;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.Controllers.QuestionController;
import server.DBWrapper;
import server.models.Question;
import server.models.Quiz;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/Question")
public class QuestionEndpoint {
    QuestionController controller = new QuestionController();

    @GET
    @Path ("{quizId}")
    public Response getQuestions(@PathParam("quizId") int quizId) {

        ArrayList<Question> question = controller.getQuestions(quizId);


//
            /*try {
                controller.getQuestions(new Gson().fromJson(jsonQuestion, Question.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }*/


        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson(question))
                .build();

    }
/*
    @GET
    @Path("{questionId}")
    public Response getQuestionById(@PathParam("questionId") int questionId) {


        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson("foundQuestion"))
                .build();

    }
*/
    @POST
    public Response createQuestion(String jsonQuestion) throws Exception {
            Boolean isCreated = controller.createQuestion(jsonQuestion);

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"question created\":"+isCreated+"}")
                .build();

    }
}