package server.endpoints;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.Controllers.QuizController;
import server.models.Quiz;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    /*
    @GET
    @Path("{id}")
    public Response getQuizById(@PathParam("id") int quizID){


        //Quiz foundQuiz;

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson("foundQuiz"))
                .build();
    }
    */


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

