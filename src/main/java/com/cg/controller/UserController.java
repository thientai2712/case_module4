package com.cg.controller;

import com.cg.model.User;
import com.cg.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {


    @Autowired
    private IUserService userService;

    @GetMapping()
    public ModelAndView showUserList(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/list");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreatePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/create");

        modelAndView.addObject("user", new User());
        return modelAndView;
    }
    @GetMapping("/update")
    public ModelAndView showUpdatePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/update");

        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView doCreate(@ModelAttribute User user){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/create");

        try{
            modelAndView.addObject("success", "Create user success");

        }catch (Exception e){
            e.printStackTrace();
            modelAndView.addObject("error", "Bad data");
        }
        modelAndView.addObject("user", new User());

        return modelAndView;
    }

}
