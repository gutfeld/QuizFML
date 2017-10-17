package server.endpoints;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.models.Question;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;





@Path("/Question")
public class QuestionEndpoint {
    @GET
    public Response getQuestion(){


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
    public Response createQuestion(String jsonUser) {

        Question newQuestion = new Gson().fromJson(JsonQuestion, Question.class);


        return Response
                .status(200)
                .type("application/json")
                .entity("{\"questionCreated\":\"true\"})
                .build();

    }
}
