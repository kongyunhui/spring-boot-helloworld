package com.kyh.service.impl;

import com.kyh.constant.StaticParams;
import com.kyh.dao.UserMapper;
import com.kyh.model.User;
import com.kyh.security.CurrentUser;
import com.kyh.security.MyUserDetailsService;
import com.kyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by kongyunhui on 2017/4/20.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User findUser(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null
                && (currentUser.getRoles().contains(StaticParams.USERROLE.ROLE_ADMIN) || currentUser.getId().equals(userId));
    }
}