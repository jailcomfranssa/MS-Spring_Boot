package com.jf.user.service.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @Column(name = "ID")
    private  String userId;

    @Column(name ="NAME", length = 20)
    private  String name;

    @Column(name ="EMAIL")
    private  String email;

    @Column(name = "ABOUT")
    private  String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();
}
