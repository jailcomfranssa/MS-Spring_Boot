package com.jf.user.service.services.impl;

import com.jf.user.service.entities.Hotel;
import com.jf.user.service.entities.Rating;
import com.jf.user.service.entities.User;
import com.jf.user.service.exceptions.ResourceNotFoundException;
import com.jf.user.service.repositories.UserRepository;
import com.jf.user.service.services.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8083/rating/user/"+user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            ResponseEntity<Hotel> forEntity =  restTemplate.getForEntity("http://localhost:8082/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = forEntity.getBody();

            rating.setHotel(hotel);

            return rating;

        }).collect(Collectors.toList());


        user.setRatings(ratingList);

        return user;
    }
}
