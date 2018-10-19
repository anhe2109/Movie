package com.mewie.mewie;

import java.util.List;

public interface MovieRepo {

    boolean createMovie(Movie movie);
    boolean deleteMovie(int index);
    Movie updateMovie(Movie movie);
    Movie getMovie(int index);
    List<Movie> getMovies();
}
