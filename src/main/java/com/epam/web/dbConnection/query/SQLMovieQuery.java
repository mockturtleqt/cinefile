package com.epam.web.dbConnection.query;

public class SQLMovieQuery {
    public static final String SQL_INSERT_MOVIE = "INSERT INTO\n" +
            "    `movie_rating`.`movie`(`title`, `release_date`, `description`, `poster`, `rating`, `genre`)\n" +
            "     VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SQL_SELECT_MOVIES_BY_NAME_PART = "SELECT \n" +
            "    `movie`.`id`,\n" +
            "    `movie`.`title`,\n" +
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

    public static final String SQL_SELECT_MOVIE_BY_TITLE = "SELECT \n" +
            "    `movie`.`id`,\n" +
            "    `movie`.`title`,\n" +
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

    public static final String SQL_SELECT_ALL_MOVIES = "SELECT \n" +
            "    `movie`.`id`,\n" +
            "    `movie`.`title`,\n" +
            "    `movie`.`release_date`,\n" +
            "    `movie`.`description`,\n" +
            "    `movie`.`poster`,\n" +
            "    `movie`.`rating`,\n" +
            "    `movie`.`genre`\n" +
            "FROM\n" +
            "    `movie_rating`.`movie`\n" +
            "WHERE\n" +
            "        `movie`.`is_deleted` = 0";

    public static final String SQL_SELECT_MOVIE_BY_ID = "SELECT \n" +
            "    `movie`.`id`,\n" +
            "    `movie`.`title`,\n" +
            "    `movie`.`release_date`,\n" +
            "    `movie`.`description`,\n" +
            "    `movie`.`poster`,\n" +
            "    `movie`.`rating`,\n" +
            "    `movie`.`genre`\n" +
            "FROM\n" +
            "    `movie_rating`.`movie`\n" +
            "WHERE\n" +
            "    `movie`.`id` = ?\n" +
            "        AND `movie`.`is_deleted` = 0";

    public static final String SQL_SELECT_MOVIES_BY_MEDIA_PERSON_ID = "SELECT \n" +
            "    `movie`.`id`,\n" +
            "    `movie`.`title`,\n" +
            "    `movie`.`release_date`,\n" +
            "    `movie`.`description`,\n" +
            "    `movie`.`poster`,\n" +
            "    `movie`.`rating`,\n" +
            "    `movie`.`genre`\n" +
            "FROM\n" +
            "    `movie_rating`.`media_person`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`movie_m2m_media_person` ON `media_person`.`id` = `movie_m2m_media_person`.`media_person_id`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`movie` ON `movie`.`id` = `movie_m2m_media_person`.`movie_id`\n" +
            "WHERE\n" +
            "    `media_person`.`id` = ?\n" +
            "        AND `media_person`.`is_deleted` = 0\n" +
            "        AND `movie`.`is_deleted` = 0";

    public static final String SQL_SELECT_TOP_10_MOVIES = "SELECT \n" +
            "    `movie`.`id`,\n" +
            "    `movie`.`title`,\n" +
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

    public static final String SQL_DELETE_MOVIE_BY_ID = "UPDATE `movie_rating`.`movie` \n" +
            "SET \n" +
            "    `is_deleted` = 1\n" +
            "WHERE\n" +
            "    `id` = ?";

    public static final String SQL_UPDATE_MOVIE = "UPDATE `movie_rating`.`movie` \n" +
            "SET \n" +
            "    `movie`.`title` = ?,\n" +
            "    `movie`.`release_date` = ?,\n" +
            "    `movie`.`description` = ?,\n" +
            "    `movie`.`poster` = ?,\n" +
            "    `movie`.`genre` = ?\n" +
            "WHERE\n" +
            "    `id` = ?";
}
