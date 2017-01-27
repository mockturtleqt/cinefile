package com.epam.web.dbConnection.query;

public class SQLMovieRatingQuery {
    public static final String SQL_SELECT_RATINGS_BY_USER_ID = "SELECT \n" +
            "    `movie_rating`.`user_id`,\n" +
            "    `movie_rating`.`movie_id`,\n" +
            "    `movie_rating`.`id`,\n" +
            "    `movie_rating`.`rate`,\n" +
            "    `movie`.`title` AS `movieTitle`\n" +
            "FROM\n" +
            "    `movie_rating`.`movie_rating`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`movie` ON `movie_rating`.`movie_id` = `movie`.`id`\n" +
            "WHERE\n" +
            "    `movie_rating`.`user_id` = ?\n" +
            "        AND `movie_rating`.`is_deleted` = 0\n" +
            "        AND `movie`.`is_deleted` = 0";

    public static final String SQL_INSERT_MOVIE_RATING = "INSERT INTO\n" +
            "    `movie_rating`.`movie_rating`(`user_id`, `movie_id`, `rate`)\n" +
            "     VALUES (?, ?, ?)";

    public static final String SQL_UPDATE_MOVIE_RATING = "UPDATE `movie_rating`.`movie_rating` \n" +
            "SET \n" +
            "    `movie_rating`.`rate` = ?\n" +
            "WHERE\n" +
            "    `id` = ?";

    public static final String SQL_DELETE_MOVIE_RATING_BY_ID = "UPDATE `movie_rating`.`movie_rating` \n" +
            "SET \n" +
            "    `is_deleted` = 1\n" +
            "WHERE\n" +
            "    `id` = ?";

    public static final String SQL_SELECT_MOVIE_RATING_BY_ID = "SELECT \n" +
            "    `movie_rating`.`id`,\n" +
            "    `movie_rating`.`user_id`,\n" +
            "    `movie_rating`.`movie_id`,\n" +
            "    `movie_rating`.`rate`\n" +
            "FROM\n" +
            "    `movie_rating`.`movie_rating`\n" +
            "WHERE\n" +
            "    `movie_rating`.`id` = ?\n" +
            "        AND `movie_rating`.`is_deleted` = 0";
}
