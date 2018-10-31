package com.mewie.mewie.Repositories;

import com.mewie.mewie.Beans.Actor;
import com.mewie.mewie.Beans.Genre;
import com.mewie.mewie.Beans.Movie;
import com.mewie.mewie.Repositories.Interfaces.MovieRepo;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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

            String stringInsert = "INSERT INTO movies VALUE (default, '" + movie.getTitle() + "', " + movie.getProductionYear() + ", "+ movie.getGenre().getGenre_id() +" ); ";
            String fkString = "";
            if (movie.getActors().size() > 0){
                fkString = createActorsFk(movie);
            }

            statement.execute(stringInsert);
            statement.execute(fkString);


            closeConnection(connection);
            return true;
        } catch (Exception e) { e.printStackTrace();}
        return false;
    }
    private String createActorsFk(Movie movie){
        String result = "INSERT INTO moviesactors VALUES (default, LAST_INSERT_ID(), " + movie.getActors().get(0).getActor_id() +")";
        for (int i = 1; movie.getActors().size() > i; i++ ){
            result += ",(default, LAST_INSERT_ID(), " + movie.getActors().get(i).getActor_id() +")";
        }
        result += ";";
        return result;
    }



    @Override
    public boolean deleteMovie(int index) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringDeleteFk = "DELETE FROM moviesactors WHERE movie_id =" + index + ";";
            String stringDelete = "DELETE FROM movies  WHERE movie_id =" + index + ";";
            statement.execute(stringDeleteFk);
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
            String deleteActors = "DELETE FROM moviesactors WHERE movie_id =" + movie.getMovie_id() + ";";
            statement.execute(deleteActors);

            for (Actor a: movie.getActors()){
                String addActor = "INSERT INTO moviesactors value (default, "+ movie.getMovie_id() + ", "+ a.getActor_id() +");";
                statement.execute(addActor);
            }
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

            String getActors = "SELECT mewie.actors.actor_id, actor_name FROM mewie.moviesactors INNER JOIN mewie.actors ON mewie.actors.actor_id = mewie.moviesactors.actor_id WHERE movie_id =" + index +"; ";
            statement.executeQuery(getActors);
            result = statement.getResultSet();

            while(result.next()){
                movie.getActors().add(new Actor(result.getInt("actor_id"), result.getString("actor_name"), 0, null));
            }
            
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
                ArrayList<Actor> actors = getActors(id);
                movies.add(new Movie(id, title, productionYear, genre, actors));
            }
            return movies;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }



    }
    ArrayList<Actor> getActors(int id) {
        ArrayList<Actor> actors = new ArrayList<>();
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringGet = "SELECT actor_name, actors.actor_id\n" +
                    "FROM movies\n" +
                    "INNER JOIN moviesactors ON moviesactors.movie_id = movies.movie_id\n" +
                    "INNER JOIN actors ON actors.actor_id = moviesactors.actor_id\n" +
                    "WHERE movies.movie_id =" + id;
            statement.executeQuery(stringGet);
            ResultSet result = statement.getResultSet();

            while (result.next()){
                String actorName = result.getString("actor_name");
                int actorId = result.getInt("actor_id");
                actors.add(new Actor(actorId, actorName, 0, null));
            }

            return actors;

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
}
