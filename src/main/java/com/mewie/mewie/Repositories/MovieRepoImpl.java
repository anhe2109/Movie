package com.mewie.mewie.Repositories;

import com.mewie.mewie.Beans.Genre;
import com.mewie.mewie.Beans.Movie;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class MovieRepoImpl extends JdbcFix implements MovieRepo {

    @Override
    public boolean createMovie(Movie movie) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringInsert = "INSERT INTO movies VALUES (default, '" + movie.getTitle() + "', ' " + movie.getProductionYear() + "' , '" + movie.getGenre().getGenre_id() + "');";
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
            String stringDelete = "DELETE FROM movies  WHERE movie_id =" + index + ";";
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
            String stringUpdate = "UPDATE movies SET title='"+ movie.getTitle() +"', genre='" + movie.getGenre().getGenre_id() +"', productionYear='"+ movie.getProductionYear()+ "' WHERE movie_id = " + movie.getMovie_id();
            statement.execute(stringUpdate);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;}
    }

    @Override
    public Movie getMovie(int index) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringGet = "SELECT * FROM movies WHERE movie_id =" + index + ";";
            statement.executeQuery(stringGet);
            ResultSet result = statement.getResultSet();
            result.next();

            Movie movie = new Movie();
            movie.setMovie_id(result.getInt("movie_id"));
            movie.getGenre().setGenre_id(result.getInt("genre"));
            movie.setProductionYear(result.getInt("productionYear"));
            movie.setTitle(result.getString("title"));
            
            return movie;

        } catch (Exception e) {
            e.printStackTrace();
            return null;} finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Movie> getMovies() {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();

            String stringSelect =
                    "SELECT\n" +
                            "       movie_id,\n" +
                            "       title,\n" +
                            "       productionYear,\n" +
                            "       genres.genre\n" +
                            "FROM mewie.movies\n" +
                            "INNER JOIN mewie.genres ON mewie.genres.genre_id = mewie.movies.genre";
            ResultSet resultSet = statement.executeQuery(stringSelect);

            List<Movie> movies = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("movie_id");
                String title = resultSet.getString("title");
                int productionYear = resultSet.getInt("productionYear");
                Genre genre = new Genre();
                genre.setGenre(resultSet.getString("genres.genre"));

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
