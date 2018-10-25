package com.mewie.mewie.Beans;

public class Genre {

    private String genre;
    private int genre_id;

    public Genre() {
    }

    public Genre(String genre, int genre_id) {
        this.genre = genre;
        this.genre_id = genre_id;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genre='" + genre + '\'' +
                ", genre_id=" + genre_id +
                '}';
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }
}
