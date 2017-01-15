package com.epam.web.dbConnection;

public class SQLQueries {
    public final static String SQL_SELECT_MOVIE = "SELECT \n" +
            "    `movie`.`id`,\n" +
            "    `movie`.`title`,\n" +
            "    `movie`.`description`,\n" +
            "    `movie`.`poster`\n" +
            "FROM\n" +
            "    `movie_rating`.`movie`\n" +
            "WHERE\n" +
            "    UPPER(`movie`.`title`) LIKE ?";

    public final static String SQL_INSERT_USER = "INSERT INTO\n" +
            "    `movie_rating`.`user`(`login`, `password`, `email`, `first_name`, `last_name`)\n" +
            "     VALUES (?, ?, ?, ?, ?)";

    public final static String SQL_SELECT_USER = "SELECT \n" +
            "    `user`.`id`,\n" +
            "    `user`.`role`,\n" +
            "    `user`.`login`,\n" +
            "    `user`.`password`\n" +
            "FROM\n" +
            "    `movie_rating`.`user`\n" +
            "WHERE\n" +
            "    `user`.`login` = ?\n" +
            "        AND `user`.`password` = ?";


}
