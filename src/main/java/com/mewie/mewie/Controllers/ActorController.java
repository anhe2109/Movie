package com.mewie.mewie.Controllers;

import com.mewie.mewie.Beans.Actor;
import com.mewie.mewie.Services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ActorController {

    @Autowired
    ActorService actorService;



}
