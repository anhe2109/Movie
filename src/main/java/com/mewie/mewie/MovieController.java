package com.mewie.mewie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("user", new User());
        return INDEX;
    }
    @PostMapping("/login")
    public String login(@ModelAttribute User user){
        LOGGER.info("enter was called... ");
        LOGGER.info(user.getUsername() + " " + user.getPassword());
        return REDIRECT_INDEX;
    }



    /*
    @GetMapping("/")
    public String index(Model model){
        LOGGER.info("index was called... ");
        List<Movie> movies = movieRepo.getMovies();
        model.addAttribute("movies", movies);
        return INDEX;
    }


    */



    @GetMapping("/create.html")
    public String create(){
        LOGGER.info("create was called... ");
        return CREATE;
    }

    @PostMapping("/create.html")
    public String createMovie(){
        LOGGER.info("createMovie was called... ");
        return REDIRECT_INDEX;

    }


}
