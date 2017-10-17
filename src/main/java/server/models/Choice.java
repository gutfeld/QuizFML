package server.models;

public class Choice {
    private int questionId;
    private int choiceId;
    private String choiceTitle;
    private boolean answer;


    //
    public Choice( int questionId, int choiceId, String choiceTitle, boolean answer){
        this.questionId = questionId;
        this.choiceId = choiceId;
        this.choiceTitle = choiceTitle;
        this.answer = answer;

    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public String getChoiceTitle() {
        return choiceTitle;
    }

    public void setChoiceTitle(String choiceTitle) {
        this.choiceTitle = choiceTitle;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
