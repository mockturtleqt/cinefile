package com.epam.web.dbConnection.query;

public class SQLMediaPersonQuery {
    public static final String SQL_SELECT_MEDIA_PEOPLE_BY_MOVIE_ID = "SELECT \n" +
            "    `media_person`.`id`,\n" +
            "    `media_person`.`bio`,\n" +
            "    `media_person`.`occupation`,\n" +
            "    `media_person`.`first_name`,\n" +
            "    `media_person`.`last_name`,\n" +
            "    `media_person`.`gender`,\n" +
            "    `media_person`.`birthday`,\n" +
            "    `media_person`.`picture`\n" +
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

    public static final String SQL_SELECT_MEDIA_PERSON_BY_ID = "SELECT \n" +
            "    `media_person`.`id`,\n" +
            "    `media_person`.`bio`,\n" +
            "    `media_person`.`occupation`,\n" +
            "    `media_person`.`first_name`,\n" +
            "    `media_person`.`last_name`,\n" +
            "    `media_person`.`gender`,\n" +
            "    `media_person`.`birthday`,\n" +
            "    `media_person`.`picture`\n" +
            "FROM\n" +
            "    `movie_rating`.`media_person`\n" +
            "WHERE\n" +
            "    `media_person`.`id` = ?\n" +
            "        AND `media_person`.`is_deleted` = 0";

    public static final String SQL_DELETE_MEDIA_PERSON_BY_ID = "UPDATE `movie_rating`.`media_person` \n" +
            "SET \n" +
            "    `is_deleted` = 1\n" +
            "WHERE\n" +
            "    `id` = ?";
}
