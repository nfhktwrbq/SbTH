package application.service;

import application.persistance.User;
import application.service.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface UserService extends BaseService<User, Long> {

    public User findUserByName(String name);

}