package com.services.autoparts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping("/")
    public ModelAndView search() {
        ModelAndView view = new ModelAndView();
        view.setViewName("parts-search");
        System.out.println("at least accessed");
        return view;
    }
}
