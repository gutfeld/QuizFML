package server.endpoints;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.Controllers.QuizController;
import server.models.Course;
import server.models.Quiz;
import server.security.XORController;
import server.models.User;
import server.Controllers.Log;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;




@Path("/quiz")
public class QuizEndpoint {

    Log log = new Log();

    QuizController controller = new QuizController();


    @GET
    @Path("{id}")
    public Response getQuizzes(@PathParam("id") int courseId) throws IOException, ClassNotFoundException {

        log.writeLog(this.getClass().getName(), this, "We are getting quizzes", 2);

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

        log.writeLog(this.getClass().getName(), this, "We are creating a quiz", 0);

        controller.createQuiz(quiz);

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

        log.writeLog(this.getClass().getName(), this, "We are now in process of deleting a quiz", 2);

        Boolean deleteQuiz = controller.deleteQuiz(quizID);


        if (deleteQuiz == true) {
            log.writeLog(this.getClass().getName(), this, "Quiz bliver slettet", 2);
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(new Gson().toJson("Den burde vÊre slettet korrekt"))
                    .build();
        } else {
            log.writeLog(this.getClass().getName(), this, "Quizzen er ikke slettet korrekt", 2);
            return Response
                    .status(200)
                    .type("application/json")
                    .entity(new Gson().toJson("Den er vidst ikke slettet korrekt"))
                    .build();
        }

}}

