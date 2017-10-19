package server.endpoints;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.Controllers.QuizController;
import server.models.Quiz;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;




@Path("/quiz")
public class QuizEndpoint {

    QuizController controller = new QuizController();

    @GET
    @Path("{id}")
    public Response getQuizzes(@PathParam("id") int courseId){

        ArrayList<Quiz> allQuizzes = controller.getQuizzes(courseId);

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson(allQuizzes))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteQuiz(@PathParam("id") int quizID) throws Exception {

        controller.deleteQuiz(quizID);

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson("Vi håber den er slettet ik'"))
                .build();
    }

    @POST
    public Response createQuiz(String quiz) throws Exception {

        controller.createQuiz(quiz);

        // tilføj den nye quiz til quizArray'et her

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"quizCreated\":\"true\"}")
                .build();
    }

}

