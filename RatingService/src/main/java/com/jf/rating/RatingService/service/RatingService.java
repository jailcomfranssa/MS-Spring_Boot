package com.jf.rating.RatingService.service;

import com.jf.rating.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);
    List<Rating> getAll();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
    Rating getSingle(Long id);
}
