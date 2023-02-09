package com.jf.user.service.services.impl;

import com.jf.user.service.entities.Rating;
import com.jf.user.service.entities.User;
import com.jf.user.service.exceptions.ResourceNotFoundException;
import com.jf.user.service.repositories.UserRepository;
import com.jf.user.service.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public User saveUser(User user) {

        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User id" + userId + "não encontrado"));

        ArrayList<Rating> ratings = restTemplate.getForObject("http://localhost:8083/rating/user/"+user.getUserId(), ArrayList.class);
        user.setRatings(ratings);

        return user;
    }
}
