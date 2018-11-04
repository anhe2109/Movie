package com.mewie.mewie.Controllers;

import com.mewie.mewie.Beans.Actor;
import com.mewie.mewie.Beans.Movie;
import com.mewie.mewie.Services.ActorService;
import com.mewie.mewie.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    ActorService actorService;

    @Autowired
    GenreController genreController;

    @Autowired
    ActorController actorController;

    private static final Logger LOGGER = Logger.getLogger(MovieController.class.getName());

    private final String INDEX = "index";
    private final String CREATE = "create";
    private final String DELETE = "delete";
    private final String UPDATE = "update";
    private final String REDIRECT_INDEX = "redirect:/";
    private final String Login = "login";
    private final String ERROR = "error";
    private final String ACTORS = "actors";
    private final String CREATE_ACTOR = "createActor";
    private final String DISPLAY = "display";
    private final String UPDATE_ACTOR = "updateActor";
    private final String REDIRECT_ACTORS = "redirect:/actors.html";


    @GetMapping("/")
    public String index(Model model){
        LOGGER.info("index was called... ");
        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);


        return INDEX;
    }

    @RequestMapping(value = "/displayMovie", method = RequestMethod.GET)
    public String displayMovie(@RequestParam(name="id")String id, Model model){
        LOGGER.info("Display movie was called" + id);
        Movie movie = movieService.getMovie(Integer.parseInt(id));
        model.addAttribute("movie", movie);
        return DISPLAY;
    }



    @GetMapping("/create.html")
    public String create(Model model, Model genreDisplay, Model actorDisplay){
        LOGGER.info("create was called... ");
        model.addAttribute("movie", new Movie());
        genreDisplay.addAttribute("genres", genreController.genreService.getGenres());
        actorDisplay.addAttribute("actors", actorController.actorService.getActors());
        return CREATE;
    }

    @RequestMapping("/saveMovie")
    public String saveMovie(@ModelAttribute Movie movie){
        LOGGER.info("saveMovie was called... ");
        System.out.println(movie);
        movieService.createMovie(movie);
        return REDIRECT_INDEX;

    }

    @RequestMapping(value = "/deleteMovie", method = RequestMethod.GET)
    public String deleteMovie(@RequestParam(name="id")String id ){
        LOGGER.info("Delete movie was called" + id);
        movieService.deleteMovie(Integer.parseInt(id));
        return REDIRECT_INDEX;
    }

    @RequestMapping(value = "/updateMovie", method = RequestMethod.GET)
    public String updateMovie(@RequestParam(name = "id") String id, Model model, Model genreDisplay, Model movieDisplay) {
        LOGGER.info("updateMovie action called... " + id);
        model.addAttribute("movie", movieService.getMovie(Integer.parseInt(id)));
        genreDisplay.addAttribute("genres", genreController.genreService.getGenres());
        movieDisplay.addAttribute("actors", actorController.actorService.getActors());
        return UPDATE;
    }

    @RequestMapping("/updateMovieSubmit")
    public String updateMovie(@ModelAttribute Movie movie){
        LOGGER.info("updateMovieSubmit was called");
        movieService.updateMovie(movie);
        return REDIRECT_INDEX;
    }


}
