package com.mewie.mewie;

import java.util.ArrayList;

public interface MovieRepo {

    boolean createMovie(Movie movie);
    boolean deleteMovie(int index);
    Movie updateMovie(Movie movie);
    Movie getMovie(int index);
    ArrayList<Movie> getMovies();
}
