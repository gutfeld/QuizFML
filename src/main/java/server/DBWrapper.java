package server;

import com.google.gson.Gson;
import server.models.Quiz;
import server.models.User;

import java.sql.*;
import java.util.ArrayList;

public class DBWrapper {

    public static final String DEFAULT_URL = "distribueredesystemer.cqsg17giwvxa.eu-central-1.rds.amazonaws.com";
    private static final String DEFAULT_USERNAME = "dis2017";
    private static final String DEFAULT_PASSWORD = "doekdis2017";

    public static Connection getConnection(String url, String username, String password) throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static User authorizeUser (String username, String password) throws Exception {
        Connection connection = getConnection(DEFAULT_URL, DEFAULT_USERNAME, DEFAULT_PASSWORD);
        ResultSet resultSet;
        User userFound = null;

        try {
            PreparedStatement authenticate = connection.prepareStatement("select * from fmldb/users where username = ? AND Password = ?");
            authenticate.setString(1, username);
            authenticate.setString(2, password);

            resultSet = authenticate.executeQuery();

            while (resultSet.next()) {
                try {
                    userFound = new User();
                    userFound.setId(resultSet.getInt("UserID"));

                } catch (SQLException e) {

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(preparedStatement);
        }
    }

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
