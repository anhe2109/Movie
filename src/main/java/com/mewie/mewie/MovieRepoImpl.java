package com.mewie.mewie;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class MovieRepoImpl implements MovieRepo {

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
    public ArrayList<Movie> getMovies() {
        return null;
    }
}
