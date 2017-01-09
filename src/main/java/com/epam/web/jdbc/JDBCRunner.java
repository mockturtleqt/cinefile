package com.epam.web.jdbc;

import com.epam.web.entity.MediaPerson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCRunner {
    static  final String SQL = "SELECT media_person_id, first_name, last_name FROM movie_rating.media_person";

    public static void main(String[] args) {
        try (
                Connection connection = ConnectorDB.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL);
        ) {
            List<MediaPerson> mediaPersons = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("media_person_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                mediaPersons.add(new MediaPerson(id, firstName, lastName));
            }
            if (mediaPersons.size() > 0) {
                System.out.println(mediaPersons);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
