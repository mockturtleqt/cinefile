//package com.epam.web.jdbc;
//
//import java.sql.*;
//
//public class Test {
//    static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root";
//    static final String USER = "root";
//    static final String PASSWORD = "mockturtle";
//    static  final String SQL = "SELECT first_name, last_name FROM movie_rating.media_person";
//
//    public static void main(String[] args) {
//        Connection connection = null;
//        Statement statement = null;
//
//        try {
//            System.out.println("Connectiong to db");
//            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(SQL);
////            System.out.println("Creating statement");
////
////            ResultSet resultSet = statement.executeQuery(sql);
////
//            while (resultSet.next()) {
//                String first_name = resultSet.getString("first_name");
//                String last_mane = resultSet.getString("last_name");
//
//                System.out.println("First name: " + first_name);
//                System.out.println("Last name: " + last_mane);
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException  e) {
//            System.out.println(e);
//        } finally {
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//        }
//
//    }
//}
