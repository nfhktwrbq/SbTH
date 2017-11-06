package application.service.impl;

import application.persistance.User;
import application.repository.UserRepository;
import application.repository.base.BaseRepository;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rus on 05.10.2017.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService
{
    private UserRepository userRepository;

    protected BaseRepository<User, Long> getRepository()
    {
        return userRepository;
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    private List<User> allUsers = new ArrayList<>();

    public List<User> getAllUsers(){
        return allUsers;
    }

    public void addUser(User user) {
        allUsers.add(user);
    }

    public User findUserByName(String name){
        return userRepository.findAll().stream().filter(x -> x.getUsername().equals(name)).findFirst().get();
    }
}
