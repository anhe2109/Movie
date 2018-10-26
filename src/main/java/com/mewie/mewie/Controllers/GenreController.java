package com.mewie.mewie.Controllers;

import com.mewie.mewie.Repositories.GenreRepo;
import com.mewie.mewie.Repositories.GenreRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GenreController {

    @Autowired
    GenreRepo genreRepo;
}
