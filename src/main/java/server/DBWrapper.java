package server;


import java.sql.*;
import server.models.*;
import java.util.ArrayList;

public class DBWrapper {

    public static final String DEFAULT_URL = "distribueredesystemer.cqsg17giwvxa.eu-central-1.rds.amazonaws.com";
    private static final String DEFAULT_USERNAME = "dis2017";
    private static final String DEFAULT_PASSWORD = "doekdis2017";

    public static Connection getConnection(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void createUser(User createUser) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String PS = "INSERT INTO fmldb.user (firstName, lastName, userName, password, type) VALUES (" + createUser.getFirstName() + ", " + createUser.getLastName() + ", " + createUser.getUsername() + ", " + createUser.getPassword() + ",1)";
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement(PS);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(preparedStatement);
        }
    }


    public static void createAdmin(User createAdmin) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String PS = "INSERT INTO fmldb.user (firstName, lastName, userName, password, type) VALUES (" + createAdmin.getFirstName() + ", " + createAdmin.getLastName() + ", " + createAdmin.getUsername() + ", " + createAdmin.getPassword() + ",2)";
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement(PS);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(preparedStatement);
        }
    }

    public static void createQuiz(Quiz quiz) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String PS = "INSERT INTO fmldb.quiz (quizTitle, course_id) VALUES (" + quiz.getQuizTitle() + ", " + quiz.getCourseID() + ")";
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement(PS);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(preparedStatement);
        }
    }

    public static void deleteQuiz(Quiz quiz) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String PS = "DELETE FROM fmldb.quiz WHERE fmldb.quiz.id = " + quiz.getQuizID();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            conn.prepareStatement(PS);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(preparedStatement);
        }
    }

    public static void createQuestion(Question question) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String PS = "INSERT INTO fmldb.question (questionTitle, quiz_id) VALUES (" + question.getQuestionTitle() + ", " + question.getQuizID() + ")";
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement(PS);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(preparedStatement);
        }
    }

    public static void deleteQuestion(Question question) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String PS = "DELETE FROM fmldb.question WHERE fmldb.question.id = " + question.getQuestionId();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            conn.prepareStatement(PS);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(preparedStatement);
        }
    }

    public static void createChoice(Choice choice) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String PS = "INSERT INTO fmldb.choice (choiceTitle, answer, question_id) VALUES (" + choice.getChoiceTitle() + ", " + choice.isAnswer() + ", " + choice.getQuestionId() + ")";
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement(PS);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(preparedStatement);
        }
    }

    public static void deleteChoice(Choice choice) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String PS = "DELETE FROM fmldb.choice WHERE fmldb.choice.id = " + choice.getChoiceId();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            conn.prepareStatement(PS);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(preparedStatement);
        }
    }
/*  // VALIDERING KAN SKE I CONTROLLER, BRUG getChoices OG TJEK HVILKEN SOM HAR ANSWER = TRUE
    public static Boolean validateChoice(Choice choice) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        String PS = "";
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL,DEFAULT_USERNAME,DEFAULT_PASSWORD);
            conn.prepareStatement(PS);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(preparedStatement);
        }
    }
*/

    public static ArrayList getUsers() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement("SELECT * FROM fmldb.user");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getString(5),rs.getString(6));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(rs);
            close(preparedStatement);
        }
        return allUsers;
    }

    public static ArrayList getCourses() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Course> allCourses = new ArrayList<>();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement("SELECT * FROM fmldb.course");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Course course = new Course(rs.getInt(1), rs.getString(2));
                allCourses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(rs);
            close(preparedStatement);
        }
        return allCourses;
    }

    public static ArrayList getQuizzes(Course course) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Quiz> allQuizzes = new ArrayList<>();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement("SELECT q.* FROM fmldb.quiz q INNER JOIN fmldb.course c ON q.course_id = c.id WHERE q.course_id =" + course.getCourseID() + ";");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Quiz quiz = new Quiz(rs.getInt(1),rs.getString(2),rs.getInt(3));
                allQuizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(rs);
            close(preparedStatement);
        }
        return allQuizzes;
    }

    public static ArrayList getQuestions(Quiz quiz) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Question> allQuestions = new ArrayList<>();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement("SELECT q.* FROM fmldb.question q INNER JOIN fmldb.quiz qz ON q.quiz_id = qz.id WHERE q.quiz_id = " + quiz.getQuizID() + ";");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Question question = new Question(rs.getInt(1),rs.getString(2),rs.getInt(3));
                allQuestions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(rs);
            close(preparedStatement);
        }
        return allQuestions;
    }


    public static ArrayList getChoices(Question question) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Choice> allChoices = new ArrayList<>();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement("SELECT c.* FROM fmldb.choice c INNER JOIN fmldb.question q ON c.question_id = q.id WHERE c.question_id =" + question.getQuestionId() + ";");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Choice choice = new Choice(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getBoolean(4));
                allChoices.add(choice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(rs);
            close(preparedStatement);
        }
        return allChoices;
    }
/*
    // Giver alle de fag som en bestemt bruger er tilmeldt
    public static ArrayList getUsersCourses (User user, Course course) {
        Connection conn = null;
        ResultSet rs = null;
        String PS = null;
        PreparedStatement preparedStatement = null;
        ArrayList<Course> courseArrayList = new ArrayList<>();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement(PS);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Course tempCourse = new Course(rs.getInt(1), rs.getString(2));
                courseArrayList.add(tempCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(rs);
            close(preparedStatement);
        }
        return courseArrayList;

    }
*/
/*
    // Giver alle brugere som er tilmeldt et bestemt fag
    public static ArrayList getCoursesUsers () {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

    }
*/
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
