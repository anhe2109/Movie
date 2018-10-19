package com.mewie.mewie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieRepo movieRepo;

    private final String INDEX = "index";
    private final String CREATE = "create";

    @GetMapping
    public String index(Model model){
        List<Movie> movies = movieRepo.getMovies();
        model.addAttribute("movies", movies);
        return INDEX;
    }

    


}
