package com.Projects.springcrud.service.impl;

import com.Projects.springcrud.dto.UserDTO;
import com.Projects.springcrud.entity.User;
import com.Projects.springcrud.repository.UserRepository;
import com.Projects.springcrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public User getUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid user id: "+id));
        return user;
    }

    @Override
    public void updateUser(Integer id, User user) {
        // check if(User.inDatabase)
        userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid User id: "+id));

        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        //if(user.inDatabase)
       User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid User id: "+id));
       userRepository.delete(user);
    }

    @Override
    public void updateName(Integer id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid User id: "+id));

        user.setName(userDTO.getName());

        userRepository.save(user);

    }
}
