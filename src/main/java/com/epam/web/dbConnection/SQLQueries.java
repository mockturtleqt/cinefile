package com.epam.web.dbConnection;

public class SQLQueries {
    public final static String SQL_SELECT_ALL_MOVIES_BY_TITLE = "SELECT \n" +
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

    public final static String SQL_SELECT_MOVIE_BY_TITLE = "SELECT \n" +
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

    public final static String SQL_SELECT_MOVIE_BY_ID = "SELECT \n" +
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

    public static final String SQL_SELECT_REVIEWS_BY_MOVIE_ID = "SELECT \n" +
            "    `user`.`login`,\n" +
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

    public static final String SELECT_REVIEWS_BY_USER_ID = "SELECT \n" +
            "    `user`.`id`,\n" +
            "    `review`.`id`,\n" +
            "    `review`.`title`,\n" +
            "    `review`.`body`,\n" +
            "    `review`.`creation_date`,\n" +
            "    `movie`.`title`\n" +
            "FROM\n" +
            "    `movie_rating`.`user`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`review` ON `review`.`user_id` = `user`.`id`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`movie` ON `review`.`movie_id` = `movie`.`id`\n" +
            "WHERE\n" +
            "    `user`.`id` = 4\n" +
            "        AND `user`.`is_deleted` = 0\n" +
            "        AND `review`.`is_deleted` = 0\n" +
            "        AND `movie`.`is_deleted` = 0";

    public final static String SQL_SELECT_TOP_10_MOVIES = "SELECT \n" +
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

    public final static String SQL_SELECT_MEDIA_PEOPLE_BY_MOVIE_ID = "SELECT \n" +
            "    `media_person`.`id`,\n" +
            "    `media_person`.`first_name`,\n" +
            "    `media_person`.`last_name`\n" +
            "FROM\n" +
            "    `movie_rating`.`media_person`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`movie_m2m_media_person` ON `media_person`.`id` = `movie_m2m_media_person`.`media_person_id`\n" +
            "        INNER JOIN\n" +
            "    `movie_rating`.`movie` ON `movie_m2m_media_person`.`movie_id` = `movie`.`id`\n" +
            "WHERE\n" +
            "    `movie_m2m_media_person`.`movie_id` = ?\n" +
            "        AND `media_person`.`is_deleted` = 0\n" +
            "        AND `movie`.`is_deleted` = 0";

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
