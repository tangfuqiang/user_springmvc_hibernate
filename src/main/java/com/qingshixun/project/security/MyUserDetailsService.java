package com.qingshixun.project.security;

import com.qingshixun.project.model.AdminModel;
import com.qingshixun.project.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private QueryService queryService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        AdminModel adminModel = queryService.queryAdminMoodel(username);
        if(adminModel==null){
            throw new BadCredentialsException("AccountNotExist");
        }
        return queryService.queryAdminMoodel(username);
    }
}
