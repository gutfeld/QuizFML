package server.endpoints;
import com.google.gson.Gson;
import server.Controllers.CourseController;
import server.Controllers.Log;
import server.models.Course;
import server.models.User;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/Courses")
public class CourseEndpoint {

    Log log = new Log();

    String demoJson = new Gson().toJson("Courses");
    @GET
    public Response getCourses() throws IOException, ClassNotFoundException {
        log.writeLog(this.getClass().getName(), this, "We are now getting courses", 2);
        CourseController courseController = new CourseController();
        ArrayList<Course> courses = courseController.getCourses();

        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson(courses))
                .build();
    }
}

