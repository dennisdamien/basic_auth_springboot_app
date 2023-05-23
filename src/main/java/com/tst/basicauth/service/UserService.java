package com.tst.basicauth.service;

import com.tst.basicauth.model.User;
import com.tst.basicauth.model.UserDetls;

import java.util.List;

public interface UserService {
    public User getDetails(Long id);
    public User saveUser(User newUser);
    public User updateUser(User updatedUser,Long id);

    public List<User> getAllUsers();
    public void approve(Long id);

    public void delete(Long id);

    public List<User> approvedUsers();

//    public  User setUnamePass(UserDetls userDetls,Long id);

}
