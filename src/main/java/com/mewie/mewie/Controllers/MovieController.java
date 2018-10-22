package com.mewie.mewie.Controllers;

import com.mewie.mewie.Beans.Movie;
import com.mewie.mewie.Repositories.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class MovieController {

    @Autowired
    MovieRepo movieRepo;

    private static final Logger LOGGER = Logger.getLogger(MovieController.class.getName());

    private final String INDEX = "index";
    private final String CREATE = "create";
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
        LOGGER.info(movie.getTitle());
        LOGGER.info(String.valueOf(movie.getProductionYear()));
        return REDIRECT_INDEX;

    }


}
