package server.Controllers;

import server.DBWrapper;
import server.models.Course;

import java.util.ArrayList;

public class CourseController {



    public ArrayList<Course> getCourses(){
        DBWrapper db = new DBWrapper();
        ArrayList<Course> courses = db.getCourses();

        for(int i = 0; i < courses.size(); i++)
        System.out.println(courses.get(i));
        return courses;
    }

}
