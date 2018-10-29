package com.mewie.mewie.Repositories;

import com.mewie.mewie.Beans.Actor;
import com.mewie.mewie.Beans.Genre;
import com.mewie.mewie.Beans.Movie;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorRepoImpl extends JdbcFix implements ActorRepo{

    @Override
    public boolean createActor(Actor actor) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringCreate = "INSERT INTO actors VALUES (default, '" + actor.getName() + "', '" + actor.getYearOfBirth() + "');'";
            statement.execute(stringCreate);
            closeConnection(connection);
            return true;
        } catch (Exception e) { e.printStackTrace();}
        return false;
    }


    @Override
    public boolean deleteActor(int index) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String deleteActor = "DELETE FROM actors WHERE actor_id =" + index + ";";
            statement.execute(deleteActor);
            return true;
        } catch (Exception e) { e.printStackTrace();}
        return false;
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
            actor.setName(result.getString("name"));
            actor.setYearOfBirth(result.getInt("yearOfBirth"));
            actor.setActor_id(result.getInt("actor_id"));

            return actor;

        } catch (Exception e) {
            e.printStackTrace();
            return null;}
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
                String name = resultSet.getString("name");
                int yearOfBirth = resultSet.getInt("yearOfBirth");
                int actor_id = resultSet.getInt("movie_id");

                actors.add(new Actor(name, yearOfBirth, actor_id));
            }
            return actors;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }    }


}
