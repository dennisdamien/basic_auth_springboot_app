package com.tst.basicauth.controller;

import com.tst.basicauth.model.User;
import com.tst.basicauth.model.UserDetls;
import com.tst.basicauth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @GetMapping("user/{id}")
    public ResponseEntity<User> getuserDetails(@PathVariable Long id){
        return new ResponseEntity<>(userService.getDetails(id), HttpStatus.OK);
    }
    @PostMapping("user/save")
    public ResponseEntity<User> addUser(@RequestBody User newUser){
        return new ResponseEntity<>(userService.saveUser(newUser),HttpStatus.CREATED);
    }

    //only approved users can create user
//    @PostMapping("user/{id}/create")
//    public ResponseEntity<User> addUser(@RequestBody User newUser){
//        return new ResponseEntity<>(userService.saveUser(newUser),HttpStatus.CREATED);
//    }
    @PutMapping("user/{id}/update")
    public ResponseEntity<User> updateDetails(@RequestBody User updatedUser,@PathVariable Long id){
        return new ResponseEntity<>(userService.updateUser(updatedUser,id),HttpStatus.OK);
    }
    @GetMapping("admin/users")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }
    @PutMapping("/admin/users/{id}/approve")
    public ResponseEntity<HttpStatus> approveUser(@PathVariable Long id){
        userService.approve(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/admin/users/{id}/delete")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/admin/users/approved")
    public ResponseEntity<List<User>> getApprovedUsers(){
        return new ResponseEntity<>(userService.approvedUsers(),HttpStatus.OK);
    }
//    @PostMapping("/user/{id}/setunamepass")
//    public ResponseEntity<User > userCredential(@RequestBody UserDetls userDetls,@PathVariable Long id){
//        return new ResponseEntity<>(userService.setUnamePass(userDetls,id),HttpStatus.OK);
//    }
}
