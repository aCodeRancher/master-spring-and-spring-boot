package com.in28minutes.learnspringsecurity.service;

import com.in28minutes.learnspringsecurity.repository.UserInfoRepository;
import com.in28minutes.learnspringsecurity.user.UserInfo;
import com.in28minutes.learnspringsecurity.user.UserInfoDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;
@Service
public class UserInfoService  implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findByName(username);

         //Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
               .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));

    }


}
