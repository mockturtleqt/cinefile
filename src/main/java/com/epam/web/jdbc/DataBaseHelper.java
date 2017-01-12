package com.epam.web.jdbc;

import com.epam.web.entity.MediaPerson;
import com.epam.web.entity.Movie;
import com.epam.web.entity.Review;
import com.epam.web.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.web.jdbc.SQLQueries.*;

public class DataBaseHelper {
    private Connection connection;

    public DataBaseHelper() throws SQLException {
        connection = ConnectorDB.getConnection();
    }

    private PreparedStatement getPreparedStatement(String SQLQuery) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQLQuery);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return preparedStatement;
    }

    private Statement getStatement() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return statement;
    }

//    public List<Movie> getMovie(String titleToFind) {
//        List<Movie> movieList = new ArrayList<>();
//        titleToFind = titleToFind.toUpperCase();
//        try {
//            PreparedStatement preparedStatement = getPreparedStatement(SQL_SELECT_MOVIE);
//            preparedStatement.setString(1, "%" + titleToFind + "%");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                int id = resultSet.getInt("movie_id");
//                String title = resultSet.getString("title");
//                String description = resultSet.getString("description");
//                String poster = resultSet.getString("poster");
//                Movie movie = new Movie(id, title);
//                movie.setDescription(description);
//                movie.setPoster(poster);
//                movieList.add(movie);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return movieList;
//    }

    public User selectUser(String login, String password) {
//        User user = null;
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getPreparedStatement(SQL_SELECT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String role = resultSet.getString("role");
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                String date = resultSet.getString("creation_date");
//                user = new User(login, password);
//                user.setRole(role);
//                Review review = new Review(title, body);
//                review.setDate(date);
//                user.addReview(review);
                User user = new User(login, password);
                user.setRole(role);
                boolean flag = false;
                for (User u : users) {
                    if (u.equals(user)) {
                        user = u;
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    users.add(user);
                }
                Review review = new Review(title, body);
                review.setDate(date);
                user.addReview(review);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users.get(0);
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = getStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String title = resultSet.getString("title");
                String body = resultSet.getString("body");
                String date = resultSet.getString("creation_date");
                User user = new User(login, password);
                user.setRole(role);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                boolean flag = false;
                for (User u : users) {
                    if (u.equals(user)) {
                        user = u;
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    users.add(user);
                }
                Review review = new Review(title, body);
                review.setDate(date);
                user.addReview(review);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users;
    }

    public boolean insertUser(User user) {
        boolean flag = false;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public boolean insertMediaPerson(PreparedStatement preparedStatement, MediaPerson mediaPerson) {
        boolean flag = false;
        try {
            preparedStatement.setInt(1, mediaPerson.getId());
            preparedStatement.setString(2, mediaPerson.getFirstName());
            preparedStatement.setString(3, mediaPerson.getLastName());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return flag;
    }

    public void closeStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
