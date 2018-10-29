package com.mewie.mewie.Controllers;

import com.mewie.mewie.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GenreController {

    @Autowired
    GenreService genreService;


}
