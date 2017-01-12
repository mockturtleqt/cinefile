package com.epam.web.jdbc;

public class SQLQueries {
    public final static String SQL_INSERT = "INSERT INTO movie_rating.media_person(first_name, last_name) VALUES (?, ?, ?)";
    public final static String SQL_SELECT_MOVIE = "SELECT movie_id, title, description, poster FROM movie_rating.movie WHERE UPPER(title) LIKE ?";
//    final static String SQL_SELECT_USER = "SELECT user_id, role, login, password FROM movie_rating.user WHERE login = ? AND password = ?";
    public final static String SQL_INSERT_USER = "INSERT INTO movie_rating.user(login, password, email, first_name, last_name) " +
            "VALUES (?, ?, ?, ?, ?)";
    //final static String SQL_SELECT_ALL_USERS = "SELECT role, login, password, first_name, last_name FROM movie_rating.user " +
    //"WHERE `role` LIKE '%signed_user%'";
    public final static String SQL_SELECT_USER = "SELECT \n" +
            "    user.user_id,\n" +
            "    user.role,\n" +
            "    user.login,\n" +
            "    user.password,\n" +
            "    review.title,\n" +
            "    review.body,\n" +
            "    review.creation_date\n" +
            "FROM\n" +
            "    movie_rating.user\n" +
            "        LEFT JOIN\n" +
            "    movie_rating.review ON `user`.`user_id` = `review`.`user_id`\n" +
            "WHERE\n" +
            "    user.login = ?\n" +
            "        AND user.password = ?";
    public final static String SQL_SELECT_ALL_USERS = "SELECT \n" +
            "    `user`.`login`,\n" +
            "    `user`.`password`,\n" +
            "            `user`.`role`,\n" +
            "    `user`.`first_name`,\n" +
            "    `user`.`last_name`,\n" +
            "            `review`.`title`,\n" +
            "            `review`.`creation_date`,\n" +
            "            `review`.`body`\n" +
            "    FROM\n" +
            "    movie_rating.user\n" +
            "    LEFT JOIN\n" +
            "    movie_rating.review ON `user`.`user_id` = `review`.`user_id`\n" +
            "    WHERE\n" +
            "    `user`.`role` LIKE '%signed_user%'";

}
