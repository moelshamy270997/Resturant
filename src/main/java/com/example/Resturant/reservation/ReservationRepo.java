package com.example.Resturant.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findReservationById(Long id);
}
