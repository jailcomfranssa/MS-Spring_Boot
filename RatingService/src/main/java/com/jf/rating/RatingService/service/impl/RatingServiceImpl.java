package com.jf.rating.RatingService.service.impl;

import com.jf.rating.RatingService.entities.Rating;
import com.jf.rating.RatingService.exceptions.ResourceNotFoundException;
import com.jf.rating.RatingService.repository.RatingRepository;
import com.jf.rating.RatingService.service.RatingService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating getSingle(Long id) {
        return ratingRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Rating id: "+id +" nao encontrado"));
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        List<Rating> user = ratingRepository.findByUserId(userId);
        if(user.isEmpty()){
            throw new ResourceNotFoundException("User id: "+ userId +" não encontrado");
        }

        return user;
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        List<Rating> hotel = ratingRepository.findByHotelId(hotelId);
        if(hotel.isEmpty()){
            throw new ResourceNotFoundException("Hotel id: "+ hotelId +" não encontrado");
        }

        return hotel;
    }
}
