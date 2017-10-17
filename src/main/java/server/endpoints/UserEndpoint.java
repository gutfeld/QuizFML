package server.endpoints;

import com.google.gson.Gson;
import server.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/User")


public class UserEndpoint {

    @GET
    public Response getUsers() {

    // ArrayList<User> users; Mangler DB her som skal give UserTable

        return Response.status(200)
                .type("application/json")
                .entity(new Gson().toJson("users"))
                .build();

    }

    @GET
    @Path("{id}")

    public Response getUserById(@PathParam("id") int UserId) {
    // User foundUser;

        return Response
            .status(200)
            .type("application/json")
            .entity(new Gson().toJson("foundUser"))
            .build();
    }

@POST
    public Response createUser(String jsonUser) {

        User newUser = new Gson().fromJson(jsonUser, User.class);

        // .addNewUserToDb metode skal bruges her

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"userCreated\":\"true\"}")
                .build();
    }


}