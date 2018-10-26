package com.mewie.mewie.Controllers;

import com.mewie.mewie.Beans.Genre;
import com.mewie.mewie.Beans.Movie;
import com.mewie.mewie.Repositories.MovieRepo;
import com.mewie.mewie.Repositories.MovieRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MovieController {

    @Autowired
    MovieRepo movieRepo;

    @Autowired
    GenreController genreController;

    private static final Logger LOGGER = Logger.getLogger(MovieController.class.getName());

    private final String INDEX = "index";
    private final String CREATE = "create";
    private final String DELETE = "delete";
    private final String UPDATE = "update";
    private final String REDIRECT_INDEX = "redirect:/";


    @GetMapping("/")
    public String index(Model model){
        LOGGER.info("index was called... ");
        List<Movie> movies = movieRepo.getMovies();
        model.addAttribute("movies", movies);


        return INDEX;
    }

    @GetMapping("/create.html")
    public String create(Model model){
        LOGGER.info("create was called... ");
        model.addAttribute("movie", new Movie());
        return CREATE;
    }

    @RequestMapping("/saveMovie")
    public String saveMovie(@ModelAttribute Movie movie){
        LOGGER.info("saveMovie was called... ");
        movieRepo.createMovie(movie);
        return REDIRECT_INDEX;

    }

    @RequestMapping(value = "/deleteMovie", method = RequestMethod.GET)
    public String deleteMovie(@RequestParam(name="id")String id ){
        LOGGER.info("Delete movie was called" + id);
        movieRepo.deleteMovie(Integer.parseInt(id));
        return REDIRECT_INDEX;
    }

    @RequestMapping(value = "/updateMovie", method = RequestMethod.GET)
    public String updateMovie(@RequestParam(name = "id") String id, Model model) {
        LOGGER.info("updateMovie action called... " + id);
        model.addAttribute("movie", movieRepo.getMovie(Integer.parseInt(id)));

        return UPDATE;
    }

    @RequestMapping("/updateMovieSubmit")
    public String updateMovie(@ModelAttribute Movie movie){
        LOGGER.info("updateMovieSubmit was called");
        movieRepo.updateMovie(movie);
        return REDIRECT_INDEX;
    }


}
