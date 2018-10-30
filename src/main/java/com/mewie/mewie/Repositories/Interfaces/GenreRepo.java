package com.mewie.mewie.Repositories.Interfaces;

import com.mewie.mewie.Beans.Genre;

import java.util.List;


public interface GenreRepo {

    List<Genre> getGenres();
    Genre getGenre(int id);


}
