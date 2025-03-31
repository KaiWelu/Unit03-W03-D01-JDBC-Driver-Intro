package com.omar;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
//    connection URL
    private static final String url = "jdbc:postgresql://localhost:5432/movies_db";
//    username for postgres
    private static final String username = "postgres";
//    password for postgres
    private static final String password = "1234";
    public static void main(String[] args) {

        Connection connection = null;


        try {
//            establishing the connection using our driver
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("connection successful");


//            CRUD Operations
//            CREATE, READ, UPDATE, DELETE

            String query = "SELECT * FROM movies ORDER BY title DESC";

//            creates a statement to make the query
            Statement statement = connection.createStatement();
//            execute the query using the statement
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();

//    public Movie(int movieId, String title, int releaseYear, int duration, String language, double rating, int directorId, int genreId, LocalDate updatedAt) {

            ArrayList<Movie> movies = new ArrayList<>();

            while(resultSet.next()){
               Movie newMovie = new Movie(
                        resultSet.getInt("movie_id"),
                        resultSet.getString("title"),
                        resultSet.getInt("release_year"),
                        resultSet.getInt("duration"),
                        resultSet.getString("language"),
                        resultSet.getDouble("rating"),
                        resultSet.getInt("director_id"),
                        resultSet.getInt("genre_id"),
                        resultSet.getDate("updated_at") ==null ? LocalDate.now() : resultSet.getDate("updated_at").toLocalDate()
                );

                System.out.println(newMovie);

            }





//            making queries

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}