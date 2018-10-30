package com.mewie.mewie.Services;

import com.mewie.mewie.Beans.Actor;
import com.mewie.mewie.Repositories.ActorRepoImpl;
import com.mewie.mewie.Repositories.Interfaces.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService implements ActorRepo {

    @Autowired
    ActorRepoImpl actorRepo;

    @Override
    public boolean createActor(Actor actor) {
        actorRepo.createActor(actor);
        return true;
    }

    @Override
    public boolean deleteActor(int index) {
        actorRepo.deleteActor(index);
        return true;
    }

    @Override
    public boolean updateActor(Actor actor) {
        actorRepo.updateActor(actor);
        return true;
    }

    @Override
    public Actor getActor(int index) {
        return actorRepo.getActor(index);
    }

    @Override
    public List<Actor> getActors() {
        return actorRepo.getActors();
    }
}