package com.jf.rating.RatingService.controller;

import com.jf.rating.RatingService.entities.Rating;
import com.jf.rating.RatingService.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating> save(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        return ResponseEntity.ok(ratingService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotel(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getSingle(@PathVariable Long ratingId){
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getSingle(ratingId));

    }


}
