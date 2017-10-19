package server.endpoints;
import com.google.gson.Gson;
import server.Controllers.QuizController;
import server.models.Quiz;
import server.security.XORController;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;




@Path("/quiz")
public class QuizEndpoint {


    QuizController controller = new QuizController();


    @GET
    @Path("{id}")
    public Response getQuizzes(@PathParam("id") int courseId) throws IOException, ClassNotFoundException {

        ArrayList<Quiz> allQuizzes = controller.getQuizzes(courseId);
        String output = new Gson().toJson(allQuizzes);
        String encryptedOutput = XORController.encryptDecryptXOR(output);
        encryptedOutput = new Gson().toJson(encryptedOutput);

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson(encryptedOutput))
                .build();
    }

    @POST
    public Response createQuiz(String quiz) throws Exception {

        Quiz foundQuiz = controller.createQuiz(quiz);
        String output = new Gson().toJson(foundQuiz);
        String encryptedOutput = XORController.encryptDecryptXOR(output);
        encryptedOutput = new Gson().toJson(encryptedOutput);

        return Response
                .status(200)
                .type("application/json")
                .entity(encryptedOutput)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteQuiz(@PathParam("id") int quizID) throws Exception {

        Boolean deleteQuiz = controller.deleteQuiz(quizID);


        if (deleteQuiz == true) {
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(new Gson().toJson("Den burde vÊre slettet korrekt"))
                    .build();
        } else {
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(new Gson().toJson("Den er vidst ikke slettet korrekt"))
                    .build();
        }

}}

