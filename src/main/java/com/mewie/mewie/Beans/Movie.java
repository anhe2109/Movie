package com.mewie.mewie.Beans;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int movie_id;
    private String title;
    private int productionYear;
    private Genre genre;
    private List<Actor> actors;


    @Override
    public String toString() {
        return "Movie{" +
                "movie_id=" + movie_id +
                ", title='" + title + '\'' +
                ", productionYear=" + productionYear +
                ", genre=" + genre +
                ", actors=" + actors +
                '}';
    }

    public Movie() {
        genre = new Genre();
        actors = new ArrayList<>();
    }

    public Movie(int id, String title, int productionYear, Genre genre, ArrayList<Actor> actors) {
        this.movie_id = id;
        this.title = title;
        this.productionYear = productionYear;
        this.genre = genre;
        this.actors = actors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void setActors(int[] actorsId) {


        for (int i = 0; actorsId.length > i; i++){
            actors.add(new Actor(actorsId[i], null, 0, null));
        }

        this.actors = actors;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
