package application.service;


import application.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //application.persistance.User user = userService.findAll().stream().filter(x -> x.getUsername().equals(s)).findFirst().get();
        application.persistance.User user = userService.findUserByName(s);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(application.persistance.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),
               true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(String role) {
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Arrays.asList(authority);
    }
}
