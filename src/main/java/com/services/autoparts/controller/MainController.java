package com.services.autoparts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping("/")
    public String search() {
        return "parts-search";
    }
}
