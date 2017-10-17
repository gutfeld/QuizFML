package server.endpoints;
import com.google.gson.Gson;
import server.models.Course;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("/Courses")
public class CourseEndpoint {

    String demoJson = new Gson().toJson("test");
    @GET
    public Response getCourse(){

        //ArrayList<Course> Course;

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson("Course"))
                .build();



    }
    @GET
    @Path("{id}")
    public Response getCourseById(@PathParam("id") int courseID){


        //Course foundCourse;

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson("foundCourse"))
                .build();
    }

    @POST
    public Response createUser(String jsonCourse) {


        Course newCourse = new Gson().fromJson(jsonCourse, Course.class);
        // tilføj den nye course til quizArray'et her

        return Response
                .status(200)
                .type("application/json")
                .entity("{\"courseCreated\":\"true\"}")
                .build();
    }

}

