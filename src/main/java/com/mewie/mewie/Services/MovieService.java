package com.mewie.mewie.Services;

import com.mewie.mewie.Beans.Movie;
import com.mewie.mewie.Repositories.Interfaces.MovieRepo;
import com.mewie.mewie.Repositories.MovieRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements MovieRepo {

    @Autowired
    MovieRepoImpl movieRepo;

    @Override
    public boolean createMovie(Movie movie) {
        movieRepo.createMovie(movie);
        return true;
    }

    @Override
    public boolean deleteMovie(int index) {
        movieRepo.deleteMovie(index);
        return true;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        movieRepo.updateMovie(movie);
        return true;
    }

    @Override
    public Movie getMovie(int index) {
        return movieRepo.getMovie(index);
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepo.getMovies();
    }
}
