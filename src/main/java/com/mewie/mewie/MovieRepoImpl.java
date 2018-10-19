package com.mewie.mewie;

import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepoImpl implements MovieRepo {

    private final String url = "jdbc:mysql://gruppe-a.cnjchjz7nynx.eu-central-1.rds.amazonaws.com:3306/mewie?useSSL=false";
    private final String username = "rewt";
    private final String password = "supersecurepassword";

    @Override
    public boolean createMovie(Movie movie) {
        return false;
    }

    @Override
    public boolean deleteMovie(int index) {
        return false;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return null;
    }

    @Override
    public Movie getMovie(int index) {
        return null;
    }

    @Override
    public List<Movie> getMovies() {
        // get the jdbcTemplate to work plx - this could get out of hand fast.
        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement()
        ) {
            String stringSelect = "SELECT * FROM movies";
            ResultSet resultSet = statement.executeQuery(stringSelect);

            List<Movie> movies = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("movie_id");
                String title = resultSet.getString("title");
                int productionYear = resultSet.getInt("productionYear");
                int genre = resultSet.getInt("genre");
                movies.add(new Movie(id, title, productionYear, genre));
            }
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
            return null;}

    }
}
