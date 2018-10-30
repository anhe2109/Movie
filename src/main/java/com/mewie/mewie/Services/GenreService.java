package com.mewie.mewie.Services;

import com.mewie.mewie.Beans.Genre;
import com.mewie.mewie.Repositories.Interfaces.GenreRepo;
import com.mewie.mewie.Repositories.GenreRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements GenreRepo {

    @Autowired
    GenreRepoImpl genreRepo;


    @Override
    public List<Genre> getGenres() {
        return genreRepo.getGenres();
    }

    @Override
    public Genre getGenre(int id) {
        return genreRepo.getGenre(id);
    }
}
