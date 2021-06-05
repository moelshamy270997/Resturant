package com.example.Resturant.reservation;

import com.example.Resturant.appuser.AppUser;
import com.example.Resturant.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String firstName;
    private String lastName;
    private Long reservationNumber;
    private LocalDateTime reservedAt;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

}
