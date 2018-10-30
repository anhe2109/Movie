package com.mewie.mewie.Repositories;

import com.mewie.mewie.Beans.Genre;
import com.mewie.mewie.Repositories.Interfaces.GenreRepo;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreRepoImpl extends JdbcFix implements GenreRepo {

    @Override
    public List<Genre> getGenres() {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();

            String stringSelect = "SELECT * FROM genres";
            ResultSet resultSet = statement.executeQuery(stringSelect);

            List<Genre> genres = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("genre_id");
                String genre = resultSet.getString("genre");

                genres.add(new Genre(id, genre));
            }
            return genres;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public Genre getGenre(int id) {
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            String stringGet = "SELECT * FROM genres WHERE genre_id =" + id + ";";
            statement.executeQuery(stringGet);
            ResultSet result = statement.getResultSet();
            result.next();

            Genre genre = new Genre();
            genre.setGenre_id(result.getInt("genre_id"));
            genre.setGenre(result.getString("genre"));

            return genre;

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
