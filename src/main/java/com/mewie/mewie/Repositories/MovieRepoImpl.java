package com.mewie.mewie.Repositories;

import com.mewie.mewie.Beans.Movie;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepoImpl extends JdbcFix implements MovieRepo {

    @Override
    public boolean createMovie(Movie movie) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringInsert = "INSERT INTO movies VALUES (default, '" + movie.getTitle() + "', ' " + movie.getProductionYear() + "' , '" + movie.getGenre() + "');";
            statement.execute(stringInsert);
            closeConnection(connection);
            return true;
        } catch (Exception e) { e.printStackTrace();}
        return false;
    }

    @Override
    public boolean deleteMovie(int index) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringDelete = "DELETE FROM movies WHERE movie_id=" + index + ";";
            statement.execute(stringDelete);
            return true;
        } catch (Exception e) { e.printStackTrace();}
        return false;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringUpdate = "SELECT FROM movies WHERE movie_id =" + movie.getTitle() + "', '" + movie.getProductionYear() + "' , '" + movie.getGenre() + "');";
            statement.execute(stringUpdate);
            return true;

        } catch (Exception e) {e.printStackTrace();}
        return false;
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
            closeConnection(connection);
        }

    }
}
