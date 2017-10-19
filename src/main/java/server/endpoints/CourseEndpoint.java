package server.endpoints;
import com.google.gson.Gson;
import server.Controllers.CourseController;
import server.models.Course;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


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

        CourseController controller = new CourseController();
        try {
            controller.createCourse(new Gson().fromJson(jsonCourse,Course.class));
        } catch (SQLException e){
            e.printStackTrace();
        }
        //Course foundCourse;

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson("foundCourse"))
                .build();
    }

}

