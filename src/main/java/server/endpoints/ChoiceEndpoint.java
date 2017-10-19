package server.endpoints;
//

import com.google.gson.Gson;
import server.Controllers.ChoiceController;
import server.models.Choice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;

@Path("/Choice")
public class ChoiceEndpoint {
    ChoiceController cController = new ChoiceController();





    @GET
    @Path("/{id}")
    public Response getChoiceById(@PathParam("id") int questionID) throws IOException{
        ArrayList<Choice> choices = cController.getChoices(questionID);


        //Choice foundChoice =

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson(choices))
                .build();

    }


    @POST
    public Response createChoice(String jsonChoice) throws Exception {
        cController.createChoice(jsonChoice);
        Choice newChoice = new Gson().fromJson(jsonChoice, Choice.class);
        //tilføj det nye choice til choiceArray her

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"choiceCreated\":\"true\"}")
                .build();


    }


}
