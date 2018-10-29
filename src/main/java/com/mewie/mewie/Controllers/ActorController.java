package com.mewie.mewie.Controllers;

import com.mewie.mewie.Beans.Actor;
import com.mewie.mewie.Repositories.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class ActorController {

    @Autowired
    ActorRepo actorRepo;

    private final String ACTORS = "actors";


    @GetMapping("/t")
    public String index2(Model model){
        LOGGER.info("index was called... ");
        List<Actor> actors = actorRepo.getActors();
        model.addAttribute("actors", actors);
        return INDEX;
    }


    @GetMapping("/actors.html")
    public String createActor(Model model){
        LOGGER.info("Create was called... ");
        model.addAttribute("actor", new Actor());
        return CREATE;
    }

    @RequestMapping("/saveActor")
    public String saveActor(@ModelAttribute Actor actor){
        LOGGER.info("saveActor was called... ");
        actorRepo.createActor(actor);
        return REDIRECT_INDEX;

    }




}
