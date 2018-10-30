package com.mewie.mewie.Repositories.Interfaces;

import com.mewie.mewie.Beans.Actor;

import java.util.List;

public interface ActorRepo {

    boolean createActor(Actor actor);
    boolean deleteActor(int index);
    boolean updateActor(Actor actor);
    Actor getActor(int index);
    List<Actor> getActors();
}
