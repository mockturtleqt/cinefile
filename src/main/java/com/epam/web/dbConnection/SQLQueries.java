package com.epam.web.dbConnection;

public class SQLQueries {
    public final static String SQL_SELECT_ALL_MOVIES_BY_TITLE = "SELECT \n" +
            "    `movie`.`id`,\n" +
            "    `movie`.`title`,\n" +
            "    `movie`.`duration`,\n" +
            "    `movie`.`release_date`,\n" +
            "    `movie`.`description`,\n" +
            "    `movie`.`poster`,\n" +
            "    `movie`.`rating`,\n" +
            "    `movie`.`genre`\n" +
            "FROM\n" +
            "    `movie_rating`.`movie`\n" +
            "WHERE\n" +
            "    UPPER(`movie`.`title`) LIKE ?\n" +
            "        AND `movie`.`is_deleted` = 0";

    public final static String SQL_SELECT_MOVIE_BY_TITLE = "SELECT \n" +
            "    `movie`.`id`,\n" +
            "    `movie`.`title`,\n" +
            "    `movie`.`duration`,\n" +
            "    `movie`.`release_date`,\n" +
            "    `movie`.`description`,\n" +
            "    `movie`.`poster`,\n" +
            "    `movie`.`rating`,\n" +
            "    `movie`.`genre`\n" +
            "FROM\n" +
            "    `movie_rating`.`movie`\n" +
            "WHERE\n" +
            "    `movie`.`title` = ?\n" +
            "        AND `movie`.`is_deleted` = 0";

    public final static String SQL_SELECT_TOP_10_MOVIES = "SELECT \n" +
            "    `movie`.`id`,\n" +
            "    `movie`.`title`,\n" +
            "    `movie`.`duration`,\n" +
            "    `movie`.`release_date`,\n" +
            "    `movie`.`description`,\n" +
            "    `movie`.`poster`,\n" +
            "    `movie`.`rating`,\n" +
            "    `movie`.`genre`\n" +
            "FROM\n" +
            "    `movie_rating`.`movie`\n" +
            "WHERE\n" +
            "    `movie`.`is_deleted` = 0\n" +
            "ORDER BY `movie`.`rating` DESC\n" +
            "LIMIT 10";

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
