package com.mewie.mewie.Beans;

public class Actor {
    private int Actor_id;
    private String name;
    private int birthYear;

    @Override
    public String toString() {
        return "Actor{" +
                "Actor_id=" + Actor_id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }

    public Actor() {
    }

    public Actor(int actor_id, String name, int birthYear) {
        Actor_id = actor_id;
        this.name = name;
        this.birthYear = birthYear;
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

