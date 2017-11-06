package application.controller;

import application.persistance.User;
import application.service.UserService;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("app")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("create-user")
    public ModelAndView createUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user-creation");
        mav.addObject("usersTbEntity", new User());
        mav.addObject("allProfiles", getProfiles());
        return mav;
    }

    @GetMapping("login")
    public ModelAndView loginView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/")
    public ModelAndView firstView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }

    @GetMapping("/hello")
    public ModelAndView helloView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");
        return mav;
    }

    @PostMapping("create-user")
    public ModelAndView createUser(@Valid User user, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            mav.setViewName("user-creation");
            mav.addObject("usersTbEntity", user);
            mav.addObject("allProfiles", getProfiles());
            return mav;
        }
        if("".equals(user.getRole())){
            user.setRole("USER");
        }
        user.setRegDate(new Date());
        userService.save(user);
        mav.addObject("allUsers", userService.findAll());
        mav.setViewName("user-info");
        logger.info("Form submitted successfully.");
        return mav;
    }

    @GetMapping("show-users")
    public ModelAndView showUsersView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user-all");
        List<User> ueList = userService.findAll();
        for (User ent : ueList) {
            logger.info(ent.getFirstName());
        }
        mav.addObject("users", ueList);
        return mav;
    }

    private List<String> getProfiles() {
        List<String> list = new ArrayList<>();
        list.add("Developer");
        list.add("Manager");
        list.add("Director");
        return list;
    }

    @GetMapping("/getUser")
    public @ResponseBody User getUser(@RequestParam String userName) {

        return userService.findUserByName(userName);
    }
}