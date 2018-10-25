package com.mewie.mewie.Beans;

public class Movie {
    private int movie_id;
    private String title;
    private int productionYear;
    private Genre genre;

    @Override
    public String toString() {
        return "Movie{" +
                "movie_id=" + movie_id +
                ", title='" + title + '\'' +
                ", productionYear=" + productionYear +
                ", genre=" + genre +
                '}';
    }

    public Movie() {
        genre = new Genre();
    }

    public Movie(int id, String title, int productionYear, Genre genre) {
        this.movie_id = id;
        this.title = title;
        this.productionYear = productionYear;
        this.genre = genre;
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
