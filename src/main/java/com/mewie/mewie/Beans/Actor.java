package com.mewie.mewie.Beans;

import java.util.ArrayList;
import java.util.List;

public class Actor {
    private int Actor_id;
    private String name;
    private int birthYear;
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "Actor_id=" + Actor_id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", movies=" + movies +
                '}';
    }

    public Actor() {
        movies = new ArrayList<>();
    }

    public Actor(int actor_id, String name, int birthYear, List<Movie> movies) {
        Actor_id = actor_id;
        this.name = name;
        this.birthYear = birthYear;
        this.movies = movies;
    }

    public int getActor_id() {
        return Actor_id;
    }

    public void setActor_id(int actor_id) {
        Actor_id = actor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}

