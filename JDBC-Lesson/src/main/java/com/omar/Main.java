package com.omar;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
//    connection URL
    private static final String url = "jdbc:postgresql://localhost:5432/movies_db";
//    username for postgres
    private static final String username = "postgres";
//    password for postgres
    private static final String password = "1234";
    public static void main(String[] args) {

//        Connection connection = null;

//        close: connection, statement, resultSet, preparedStatement

        try(Connection connection = DriverManager.getConnection(url,username,password);
) {
//            establishing the connection using our driver
            System.out.println("connection successful");


//            CRUD Operations
//            CREATE, READ, UPDATE, DELETE


//            READ: all movies

//            List<Movie> movies = getAllMovies(connection);

//            movies.forEach(System.out::println);

//            CREATE: add 1 movie to our table
//            insertMovie(connection,"DROP TABLE movies;",1992,9.1);

            getMovieStartingWith(connection,"T");







//            making queries

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public static List<Movie> getAllMovies(Connection connection) {
//        Statement statement = null;
//        ResultSet resultSet = null;
        String query = "SELECT * FROM movies ORDER BY title DESC";

        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query);) {

//            creates a statement to make the query

//            execute the query using the statement



            ArrayList<Movie> movies = new ArrayList<>();

            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("movie_id"),
                        resultSet.getString("title"),
                        resultSet.getInt("release_year"),
                        resultSet.getInt("duration"),
                        resultSet.getString("language"),
                        resultSet.getDouble("rating"),
                        resultSet.getInt("director_id"),
                        resultSet.getInt("genre_id"),
                        resultSet.getDate("updated_at") == null ? LocalDate.now() : resultSet.getDate("updated_at").toLocalDate()
                ));

            }
            return movies;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally{
//            if(resultSet != null) resultSet.close();
//            if(statement != null) statement.close();
        }


    }

//    pulp fiction
//    1994
//    DROP TABLE movies;

    public static void insertMovie(Connection connection,String title, int releaseYear, double rating){
        String query = "INSERT INTO movies(title,release_year,rating) VALUES(?, ?, ?)";
//        String query = String.format("INSERT INTO movies(title,release_year,rating) VALUES(%s,%d,%f)",title,releaseYear,rating);

//        try with resources automatically closes my prepared statement
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){

//            protects against sql injections
            preparedStatement.setString(1,title);
            preparedStatement.setInt(2,releaseYear);
            preparedStatement.setDouble(3,rating);

            int rowsAdded = preparedStatement.executeUpdate();

            System.out.println("Rows inserted " + rowsAdded);
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public static void getMovieStartingWith(Connection connection, String startingChar){
        String query = "SELECT * FROM movies WHERE title ILIKE ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, startingChar + "%");
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    System.out.println(resultSet.getString("title"));
                }
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
    }

//    Exercise 1: Write a method that gets all the movies that have a title that starts with a letter
//    findMovieStartingWith(Connection connection, String startingChar)
}