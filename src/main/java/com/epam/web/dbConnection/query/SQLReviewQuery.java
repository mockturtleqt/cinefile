package com.epam.web.dbConnection.query;

public class SQLReviewQuery {


    public static final String SQL_SELECT_REVIEWS_BY_MOVIE_ID = "SELECT \n" +
            "    `user`.`login`,\n" +
            "    `review`.`user_id`,\n" +
            "    `review`.`movie_id`,\n" +
            "    `review`.`id`,\n" +
            "    `review`.`title`,\n" +
            "    `review`.`body`,\n" +
            "    `review`.`creation_date`\n" +
            "FROM\n" +
            "    `movie_rating`.`review`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`user` ON `user`.`id` = `review`.`user_id`\n" +
            "WHERE\n" +
            "    `review`.`movie_id` = ?\n" +
            "        AND `user`.`is_deleted` = 0\n" +
            "        AND `review`.`is_deleted` = 0";

    public static final String SQL_SELECT_REVIEWS_BY_USER_ID = "SELECT \n" +
            "    `review`.`user_id`,\n" +
            "    `review`.`movie_id`,\n" +
            "    `review`.`id`,\n" +
            "    `review`.`title`,\n" +
            "    `review`.`body`,\n" +
            "    `review`.`creation_date`,\n" +
            "    `movie`.`title` AS `movieTitle`\n" +
            "FROM\n" +
            "    `movie_rating`.`review`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`movie` ON `review`.`movie_id` = `movie`.`id`\n" +
            "WHERE\n" +
            "    `review`.`user_id` = ?\n" +
            "        AND `review`.`is_deleted` = 0\n" +
            "        AND `movie`.`is_deleted` = 0";

    public static final String SQL_INSERT_REVIEW = "INSERT INTO\n" +
            "    `movie_rating`.`review`(`title`, `body`, `user_id`, `movie_id`, `creation_date`)\n" +
            "     VALUES (?, ?, ?, ?, ?)";

    public static final String SQL_DELETE_REVIEW_BY_ID = "UPDATE `movie_rating`.`review` \n" +
            "SET \n" +
            "    `is_deleted` = 1\n" +
            "WHERE\n" +
            "    `id` = ?";

    public static final String SQL_UPDATE_REVIEW = "UPDATE `movie_rating`.`review` \n" +
            "SET \n" +
            "    `review`.`title` = ?,\n" +
            "    `review`.`body` = ?,\n" +
            "    `review`.`creation_date` = ?\n" +
            "WHERE\n" +
            "    `id` = ?";

    public static final String SQL_SELECT_REVIEW_BY_ID = "SELECT \n" +
            "    `review`.`id`,\n" +
            "    `review`.`user_id`,\n" +
            "    `review`.`movie_id`,\n" +
            "    `review`.`title`,\n" +
            "    `review`.`body`,\n" +
            "    `review`.`creation_date`\n" +
            "FROM\n" +
            "    `movie_rating`.`review`\n" +
            "WHERE\n" +
            "    `review`.`id` = ?\n" +
            "        AND `review`.`is_deleted` = 0";
}
