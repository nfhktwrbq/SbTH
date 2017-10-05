package com.thmlf.th;

import org.springframework.stereotype.Service;
import com.thmlf.th.UsersTbEntity;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<UsersTbEntity> allUsers = new ArrayList<>();
    public List<UsersTbEntity> getAllUserArticles(){
        return allUsers;
    }
    public void addUser(UsersTbEntity user) {
        allUsers.add(user);
    }
}