package com.mewie.mewie.Repositories;

import com.mewie.mewie.Beans.Movie;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepoImpl extends JdbcFix implements MovieRepo {

    @Override
    public boolean createMovie(Movie movie) {
        try {
            System.out.println("createmovie was called in movierepo");
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringInsert = "INSERT INTO movies VALUES (default, '" + movie.getTitle() + "', ' " + movie.getProductionYear() + "' , '" + movie.getGenre() + "');";
            statement.execute(stringInsert);
            return true;
        } catch (Exception e) { e.printStackTrace();}
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
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();

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
            return null;
        } finally {
            try {connection.close();} catch (Exception e) {e.printStackTrace();}
        }

    }
}
