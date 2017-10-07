package application.service.impl;

import application.persistance.User;
import application.repository.UserlRepository;
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
    private UserlRepository userlRepository;

    protected BaseRepository<User, Long> getRepository()
    {
        return userlRepository;
    }

    @Autowired
    public UserServiceImpl(UserlRepository hotelrepository)
    {
        this.userlRepository = hotelrepository;
    }

    private List<User> allUsers = new ArrayList<>();

    public List<User> getAllUserArticles(){
        return allUsers;
    }

    public void addUser(User user) {
        allUsers.add(user);
    }
}
