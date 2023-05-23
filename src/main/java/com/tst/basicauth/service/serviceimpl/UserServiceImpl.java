package com.tst.basicauth.service.serviceimpl;

import com.tst.basicauth.model.User;
import com.tst.basicauth.model.UserDetls;
import com.tst.basicauth.repository.UserRepository;
import com.tst.basicauth.security.SecurityConfig;
import com.tst.basicauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User getDetails(Long id) {

        User user=unWrapper(userRepository.findById(id),id);

        return user;
    }

    @Override
    public User saveUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }


    @Override
    public User updateUser(User updatedUser, Long id){
        User existingUser=unWrapper(userRepository.findById(id),id);
        if(existingUser.getApprovedStatus()){
        existingUser.setPhnNo(updatedUser.getPhnNo());
        return userRepository.save(existingUser);
        }
        else{
            throw  new RuntimeException("Access Denied");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void approve(Long id) {
        User approvedUser=unWrapper(userRepository.findById(id),id);
        approvedUser.setApprovedStatus(Boolean.TRUE);
        userRepository.save(approvedUser);
    }

    @Override
    public void delete(Long id) {
        User user=unWrapper(userRepository.findById(id),id);
        userRepository.deleteById(id);
    }

    @Override
    public List<User> approvedUsers() {
        List<User> approvedUsers=new ArrayList<>();
        List<User> allUsers=userRepository.findAll();
        for(User user:allUsers){
            if(user.getApprovedStatus()){
                approvedUsers.add(user);
            }
        }
        return approvedUsers;
    }

//    @Override
//    public User setUnamePass(UserDetls userDetls,Long id) {
//        User user=unWrapper(userRepository.findById(id),id);
//        user.setUserName(userDetls.getUserName());
//        user.setPassword(passwordEncoder.encode(userDetls.getPassword()));
//        return userRepository.save(user);
//    }

//    @Override
//    public String getUsernamePass(UserDetls userDetls) {
//
//        return "user registered !";
//    }

    public static User unWrapper(Optional<User> entity, Long id){
        if(entity.isPresent()){
            return entity.get();
        }else{
            throw new RuntimeException("User with id :"+id+" not found !");
        }
    }
}
