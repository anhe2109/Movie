package com.mewie.mewie.Repositories;

import com.mewie.mewie.Beans.Actor;
import com.mewie.mewie.Beans.Movie;
import com.mewie.mewie.Repositories.Interfaces.ActorRepo;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorRepoImpl extends JdbcFix implements ActorRepo {

    @Override
    public boolean createActor(Actor actor) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringInsert = "INSERT INTO actors VALUES (default,'" + actor.getName() + "','" + actor.getBirthYear() + "','" + actor.getMovies() + "');";
            statement.execute(stringInsert);
            closeConnection(connection);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteActor(int index) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringDelete = "DELETE FROM actors  WHERE actor_id =" + index + ";";
            statement.execute(stringDelete);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateActor(Actor actor) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringUpdate = "UPDATE actors SET actor_name='" + actor.getName() + "', yearOfBirth='" + actor.getBirthYear() + "' WHERE actor_id = " + actor.getActor_id();
            statement.execute(stringUpdate);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Actor getActor(int index) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringGet = "SELECT * FROM actors WHERE actor_id =" + index + ";";
            statement.executeQuery(stringGet);
            ResultSet result = statement.getResultSet();
            result.next();

            Actor actor = new Actor();
            actor.setActor_id(result.getInt("actor_id"));
            actor.setBirthYear(result.getInt("yearOfBirth"));
            actor.setName(result.getString("actor_name"));

            return actor;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Actor> getActors() {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();

            String stringSelect = "SELECT * FROM actors";
            ResultSet resultSet = statement.executeQuery(stringSelect);

            List<Actor> actors = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("actor_id");
                String title = resultSet.getString("actor_name");
                int birthYear = resultSet.getInt("yearOfBirth");
                ArrayList<Movie> movies = getMovies(id);
                actors.add(new Actor(id, title, birthYear, movies));
            }
            return actors;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }



    }


    private ArrayList<Movie> getMovies(int id) {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringGet = "SELECT title, movies.movie_id\n" +
                    "FROM actors\n" +
                    "INNER JOIN moviesactors ON moviesactors.actor_id = actors.actor_id\n" +
                    "INNER JOIN movies ON movies.movie_id = moviesactors.movie_id\n" +
                    "WHERE actors.actor_id =" + id;
            statement.executeQuery(stringGet);
            ResultSet result = statement.getResultSet();

            while (result.next()){
                String movieTitle = result.getString("title");
                int movieId = result.getInt("movie_id");
                movies.add(new Movie(movieId, movieTitle, 0, null, null));
            }

            return movies;

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