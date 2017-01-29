package com.epam.web.dbConnection.query;

public class SQLUserQuery {
    public static final String SQL_SELECT_USER_BY_ID = "SELECT \n" +
            "    `user`.`id`,\n" +
            "    `user`.`role`,\n" +
            "    `user`.`login`,\n" +
            "    `user`.`password`,\n" +
            "    `user`.`email`,\n" +
            "    `user`.`first_name`,\n" +
            "    `user`.`last_name`,\n" +
            "    `user`.`gender`,\n" +
            "    `user`.`birthday`,\n" +
            "    `user`.`picture`,\n" +
            "    `user`.`user_rating`,\n" +
            "    `user`.`is_banned`\n" +
            "FROM\n" +
            "    `movie_rating`.`user`\n" +
            "WHERE\n" +
            "    `user`.`id` = ?\n" +
            "        AND `user`.`is_deleted` = 0";


    public static final String SQL_SELECT_USERS_WHO_RATED_THIS_MOVIE = "SELECT \n" +
            "    `user`.`id`,\n" +
            "    `user`.`user_rating`,\n" +
            "    `movie`.`rating`,\n" +
            "    `movie_rating`.`rate`\n" +
            "FROM\n" +
            "    `movie_rating`.`movie_rating`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`user` ON `movie_rating`.`user_id` = `user`.`id`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`movie` ON `movie_rating`.`movie_id` = `movie`.`id`\n" +
            "WHERE\n" +
            "    `movie_rating`.`movie_id` = ?\n" +
            "        AND `movie_rating`.`is_deleted` = 0\n";

    public static final String SQL_INSERT_USER = "INSERT INTO\n" +
            "    `movie_rating`.`user`(`login`, `password`, `email`, `first_name`, `last_name`)\n" +
            "     VALUES (?, ?, ?, ?, ?)";

    public static final String SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT \n" +
            "    `user`.`id`,\n" +
            "    `user`.`role`,\n" +
            "    `user`.`login`,\n" +
            "    `user`.`password`,\n" +
            "    `user`.`email`,\n" +
            "    `user`.`first_name`,\n" +
            "    `user`.`last_name`,\n" +
            "    `user`.`gender`,\n" +
            "    `user`.`birthday`,\n" +
            "    `user`.`picture`,\n" +
            "    `user`.`user_rating`,\n" +
            "    `user`.`is_banned`\n" +
            "FROM\n" +
            "    `movie_rating`.`user`\n" +
            "WHERE\n" +
            "    `user`.`login` = ?\n" +
            "        AND `user`.`password` = ?\n" +
            "        AND `user`.`is_deleted` = 0";

    public static final String SQL_UPDATE_USER_RATING = "UPDATE `movie_rating`.`user` \n" +
            "SET \n" +
            "    `user_rating` = ?\n" +
            "WHERE\n" +
            "    `id` = ?";

    public static final String SQL_DELETE_USER_BY_ID = "UPDATE `movie_rating`.`user` \n" +
            "SET \n" +
            "    `is_deleted` = 1\n" +
            "WHERE\n" +
            "    `id` = ?";

    public static final String SQL_UPDATE_USER = "UPDATE `movie_rating`.`user` \n" +
            "SET \n" +
            "    `user`.`email` = ?,\n" +
            "    `user`.`first_name` = ?,\n" +
            "    `user`.`last_name` = ?,\n" +
            "    `user`.`gender` = ?,\n" +
            "    `user`.`birthday` = ?,\n" +
            "    `user`.`picture` = ?\n" +
            "WHERE\n" +
            "    `id` = ?";

    public static final String SQL_BAN_USER = "UPDATE `movie_rating`.`user` \n" +
            "SET \n" +
            "    `is_banned` = 1\n" +
            "WHERE\n" +
            "    `id` = ?";
}
