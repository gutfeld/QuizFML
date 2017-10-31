package server.Controllers;

import com.google.gson.Gson;
import server.DBWrapper;
import server.models.Choice;
import server.endpoints.ChoiceEndpoint;
import server.models.Question;

import java.io.IOException;
import java.util.ArrayList;

public class ChoiceController {


        Gson gson;
        DBWrapper db = new DBWrapper();
        Log log = new Log();

        public ChoiceController() {
            this.gson = gson;
        }

        public ArrayList<Choice> getChoices(int questionID) throws IOException {
            log.writeLog(this.getClass().getName(), this, "We are now getting choices", 0);
            ArrayList<Choice> choices = db.getChoices(questionID);
            return choices;
        }


        public Choice createChoice(Choice choice) throws Exception {
            log.writeLog(this.getClass().getName(), this, "We are now creating a choice", 0);
            Choice createdChoice = db.createChoice(choice);

            return createdChoice;
        }



}











