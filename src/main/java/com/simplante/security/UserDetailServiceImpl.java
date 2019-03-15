package com.simplante.security;

import com.simplante.model.UserApp;
import com.simplante.service.UserAppService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private UserAppService userAppService;

    public UserDetailServiceImpl(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = new UserApp();
        try {
            userApp = userAppService.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (username==null)
            throw new UsernameNotFoundException("INVALID USER");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        userApp.getRoles().forEach(roleApp ->
                authorities.add(new SimpleGrantedAuthority(roleApp.getRole())));
        return new User(userApp.getUsername(),userApp.getPassword(),authorities);
    }
}
