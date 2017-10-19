package server.models;

public class Question {

    private int quiz_id;
    private int questionId;
    private String questionTitle;


    public Question(int quiz_id, String questionTitle, int questionId) {

        this.quiz_id = quiz_id;
        this.questionTitle = questionTitle;
        this.questionId = questionId;

    }

    public Question() {

    }

    public int getQuizID() {
        return quiz_id;
    }

    public void setQuizID(int quizID) {
        this.quiz_id = quizID;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

}