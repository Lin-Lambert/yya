package com.lambert.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("page")
public class PageController {

    @RequestMapping("{pagename}")
    public ModelAndView goPage(@PathVariable("pagename") String pagename) {
        return new ModelAndView(pagename);
    }

}
