package com.example.batchprocessing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value="/",method= RequestMethod.GET)
    public String index() {

        return "index";
    }

    @RequestMapping(value="home/login",method= RequestMethod.GET)
    public String login() {

        return "login";
    }

    @RequestMapping(value="home/batch",method= RequestMethod.GET)
    public String batch() {

        return "batch";
    }

    @RequestMapping(value="home/register",method= RequestMethod.GET)
    public String register() {

        return "register";
    }

    @RequestMapping(value="home/list",method= RequestMethod.GET)
    public String list() {

        return "list";
    }

}
