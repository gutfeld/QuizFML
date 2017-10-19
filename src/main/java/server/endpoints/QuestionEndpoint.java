package server.endpoints;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.Controllers.QuestionController;
import server.DBWrapper;
import server.models.Question;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Path("/Question")
public class QuestionEndpoint {
    @GET
        public Response getQuestion(String jsonQuestion) {

            QuestionController controller = new QuestionController();
            try {
                controller.createQuestion(new Gson().fromJson(jsonQuestion, Question.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }


        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson("questions"))
                .build();

    }

    @GET
    @Path ("{questionId}")
    public Response getQuestionById(@PathParam("questionId") int questionId){


        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson("foundQuestion"))
                .build();

    }

    @POST
    public Response createQuestion(String jsonQuestion) {

        QuestionController controller = new QuestionController();
        try {
            controller.createQuestion(new Gson().fromJson(jsonQuestion, Question.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"questionCreated\":\"true\"}")
                .build();

    }
}
