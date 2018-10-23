package com.mewie.mewie.Repositories;

import com.mewie.mewie.Beans.Movie;

import java.util.List;

public interface MovieRepo {

    boolean createMovie(Movie movie);
    boolean deleteMovie(int index);
    boolean updateMovie(Movie movie);
    Movie getMovie(int index);
    List<Movie> getMovies();
}
