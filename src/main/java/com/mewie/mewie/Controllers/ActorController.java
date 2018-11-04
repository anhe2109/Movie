package com.mewie.mewie.Controllers;

import com.mewie.mewie.Beans.Actor;
import com.mewie.mewie.Services.ActorService;
import com.mewie.mewie.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class ActorController {

    @Autowired
    MovieService movieService;

    @Autowired
    ActorService actorService;

    @Autowired
    ActorController actorController;

    private final String REDIRECT_INDEX = "redirect:/";
    private final String ACTORS = "actors";
    private final String CREATE_ACTOR = "createActor";
    private final String DISPLAY = "displayActor";
    private final String UPDATE_ACTOR = "updateActor";
    private final String REDIRECT_ACTORS = "redirect:/actors.html";

    private static final Logger LOGGER = Logger.getLogger(MovieController.class.getName());


    @RequestMapping(value = "/displayActor", method = RequestMethod.GET)
    public String displayMovie(@RequestParam(name="id")String id, Model model){
        LOGGER.info("Display actors was called " + id);
        Actor actor = actorService.getActor(Integer.parseInt(id));
        model.addAttribute("actor", actor);
        return DISPLAY;
    }


    @GetMapping("/actors.html")
    public String actors(Model model){
        LOGGER.info("actors was called . . . ");
        List<Actor> actors = actorController.actorService.getActors();
        model.addAttribute("actors", actors);

        return ACTORS;
    }

    @GetMapping("/createActor.html")
    public String createActor(Model model, Model movieDisplay){
        LOGGER.info("create actor was called... ");
        model.addAttribute("actor", new Actor());
        movieDisplay.addAttribute("actors", movieService.getMovies());
        return CREATE_ACTOR;
    }

    @RequestMapping("/saveActor")
    public String saveActor(@ModelAttribute Actor actor) {
        LOGGER.info("saveActor was called... ");
        actorController.actorService.createActor(actor);
        return REDIRECT_ACTORS;
    }

    @RequestMapping(value = "/deleteActor", method = RequestMethod.GET)
    public String deleteActor(@RequestParam(name="id")String id ){
        LOGGER.info("Delete actor was called " + id);
        actorController.actorService.deleteActor(Integer.parseInt(id));
        return REDIRECT_ACTORS;
    }

    @RequestMapping(value = "/updateActor", method = RequestMethod.GET)
    public String updateMovie(@RequestParam(name = "id") String id, Model model) {
        LOGGER.info("updateActor action called... " + id);
        model.addAttribute("actor", actorService.getActor(Integer.parseInt(id)));
        return UPDATE_ACTOR;
    }

    @RequestMapping("/updateActorSubmit")
    public String updateMovie(@ModelAttribute Actor actor){
        LOGGER.info("updateMovieSubmit was called");
        actorService.updateActor(actor);
        return REDIRECT_ACTORS;
    }

}
