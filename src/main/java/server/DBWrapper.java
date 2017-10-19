package server;

import com.google.gson.Gson;
import server.models.Quiz;
import server.models.User;

import java.sql.*;
import java.util.ArrayList;

import server.models.*;

public class DBWrapper {
/*Når i arbejder lokalt, så lav to streger foran DEFAULT_URL og fjerne de to fra den neden under.
* Derefter skal man erstarte DEFAULT_USERNAME til jeres lokale database navn og DEFAULT_PASSWORD til jeres lokale
* pass.*/
    public static final String DEFAULT_URL = "jdbc:mysql://distribueredesystemer.cqsg17giwvxa.eu-central-1.rds.amazonaws.com:3306/fmldb";
   // public static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/fmldb";
    private static final String DEFAULT_USERNAME = "dis2017";
    private static final String DEFAULT_PASSWORD = "doekdis2017";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConnection(String url, String username, String password) throws SQLException {
        try {

            try {
                Class.forName(JDBC_DRIVER).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, username, password);
    }

    public static User authorizeUser (String username, String password) throws Exception {
        Connection connection = getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
        User userFound = null;

        try {
            PreparedStatement authenticate = connection.prepareStatement("select * from user where username = ? AND password = ?");
            authenticate.setString(1, username);
            authenticate.setString(2, password);

            ResultSet resultSet = authenticate.executeQuery();

            while (resultSet.next()) {
                try {
                    /*
                    userFound = new User(
                        resultSet.getString("userName"),
                        resultSet.getString("password")
                    );*/
                    userFound = new User();
                    userFound.setUsername(resultSet.getString("userName"));
                    userFound.setPassword(resultSet.getString("password"));
                    /*
                    userFound = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("firstName"),
                            resultSet.getString("lastName"),
                            resultSet.getString("userName"),
                            resultSet.getString("password"),
                            resultSet.getInt("type")
                    );*/

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userFound;
    }


    public static void createUser(User createUser) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        //String PS = "INSERT INTO user (firstName, lastName, userName, password, type) VALUES (?,?,?,?,?)";

        //String PS = "INSERT INTO user (firstName) VALUES (" + createUser.getFirstName()+")";
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement("INSERT INTO user (firstName, lastName, userName, password, type) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, createUser.getFirstName());
            preparedStatement.setString(2, createUser.getLastName());
            preparedStatement.setString(3, createUser.getUsername());
            preparedStatement.setString(4, createUser.getPassword());
            preparedStatement.setInt(5, createUser.getType());
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
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement("INSERT INTO fmldb.quiz (quizTitle, course_id) VALUES (?,?)");
            preparedStatement.setString(1,quiz.getQuizTitle());
            preparedStatement.setInt(2,quiz.getCourseID());
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

    public static ArrayList<User> getUsers() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement("SELECT * FROM user");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),rs.getInt(6));
                allUsers.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(rs);
            close(preparedStatement);
        }
        return allUsers;
    }

    public static ArrayList<Course> getCourses() {
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

   /* public static ArrayList<Quiz> getQuizzes(int courseId) {
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
*/
    public static ArrayList<Question> getQuestions(Quiz quiz) {
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


    public static ArrayList<Choice> getChoices(Question question) {
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

    // Giver alle de fag som en bestemt bruger er tilmeldt
    public static ArrayList<Course> getUsersCourses (User user) {
        Connection conn = null;
        ResultSet rs = null;
        String PS = "SELECT c.* FROM fmldb.user_course uc INNER JOIN fmldb.user u ON u.id = uc.user_id INNER JOIN fmldb.course c ON uc.course_id = c.id WHERE u.id =" + user.getUserId();
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


    // Giver alle brugere som er tilmeldt et bestemt fag s
    public static ArrayList<User> getCoursesUsers (Course course) {
        Connection conn = null;
        ResultSet rs = null;
        String PS = "SELECT u.* FROM fmldb.user_course uc INNER JOIN fmldb.course c ON uc.course_id = c.id INNER JOIN fmldb.user u ON uc.user_id = u.id WHERE c.id =" + course.getCourseID();
        PreparedStatement preparedStatement = null;
        ArrayList<User> userArrayList = new ArrayList<>();
        try {
            conn = DBWrapper.getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
            preparedStatement = conn.prepareStatement(PS);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User tempUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                userArrayList.add(tempUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(rs);
            close(preparedStatement);
        }
        return userArrayList;
    }

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
