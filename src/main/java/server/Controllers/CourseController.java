package server.Controllers;

import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Course;

import java.util.ArrayList;

public class CourseController {
    Gson gson;
    DBWrapper db = new DBWrapper();



   public ArrayList<Course> getCourses(){
         DBWrapper db = new DBWrapper();
        ArrayList<Course> courses = db.getCourses();

       for(int i = 0; i < courses.size(); i++)
       System.out.println(courses.get(i));
        return courses;
   }


    public ArrayList <Course> getCourses(){

        ArrayList<Course> c = db.getCourses();
        return c;
    }
}
