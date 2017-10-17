package server.endpoints;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.models.Quiz;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;




@Path("/quiz")
public class QuizEndpoint {
    String demoJson = new Gson().toJson("test");
    @GET
    public Response getQuizs(){

        //ArrayList<Quiz> quizs;

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson("quizs"))
                        .build();
    }


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

    @POST
    public Response createUser(String jsonQuiz) {


        Quiz newQuiz = new Gson().fromJson(jsonQuiz, Quiz.class);
        // tilføj den nye quiz til quizArray'et her

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"quizCreated\":\"true\"}")
                .build();
    }

}

