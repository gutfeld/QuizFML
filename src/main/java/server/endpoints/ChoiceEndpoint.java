package server.endpoints;
//

import com.google.gson.Gson;
import server.models.Choice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/Choice")
public class   ChoiceEndpoint {

    @GET
    public Response getChoices(){

        //ArrayList<Choice> choice;
    return Response
            .status(200)
            .type("application/json")
            .entity(new Gson().toJson("choice"))
            .build();

    }

    @GET
    @Path("{id}")
    public Response getChoiceById(@PathParam("id") int choiceID){

        //Choice foundChoice

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson("foundChoice"))
                .build();

    }


    @POST
    public Response createChoice(String jsonChoice) {

        Choice newChoice = new Gson().fromJson(jsonChoice, Choice.class);
        //tilføj det nye choice til choiceArray her

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"choiceCreated\":\"true\"}")
                .build();


    }


}


