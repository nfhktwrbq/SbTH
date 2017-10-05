package com.thmlf.th;

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
import java.util.List;

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
        mav.addObject("usersTbEntity", new UsersTbEntity());
        mav.addObject("allProfiles", getProfiles());
        return mav;
    }
    @PostMapping("create-user")
    public ModelAndView createUser(@Valid UsersTbEntity usersTbEntity, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            mav.setViewName("user-creation");
            mav.addObject("usersTbEntity", usersTbEntity);
            mav.addObject("allProfiles", getProfiles());
            return mav;
        }
        userService.addUser(usersTbEntity);
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        /*UsersTbEntity contactEntity = new UsersTbEntity();
        //contactEntity.setRegDate(new java.util.Date());
        contactEntity.setFirstName("qqq");
        contactEntity.setLastName("VN");
        contactEntity.setMarried(true);
        contactEntity.setProfile("Dev");
        contactEntity.setGender("Male");
        session.save(contactEntity);*/

        usersTbEntity.setRegDate(new java.util.Date());
        session.save(usersTbEntity);
        session.getTransaction().commit();
        session.close();
        mav.addObject("allUsers", userService.getAllUserArticles());
        mav.setViewName("user-info");
        logger.info("Form submitted successfully.");
        return mav;
    }
    @GetMapping("show-users")
    public ModelAndView showUsersView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user-all");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        List<UsersTbEntity> ueList = session.createQuery("from UsersTbEntity").list();
        for (UsersTbEntity ent : ueList) {
            logger.info(ent.getFirstName());
        }
        mav.addObject("users", ueList);
        session.close();
        return mav;
    }
    private List<String> getProfiles() {
        List<String> list = new ArrayList<>();
        list.add("Developer");
        list.add("Manager");
        list.add("Director");
        return list;
    }
}