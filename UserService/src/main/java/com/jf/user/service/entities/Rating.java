package com.jf.user.service.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Rating {

    @Id
    private String ratinId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;
}
