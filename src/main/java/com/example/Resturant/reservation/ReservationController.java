package com.example.Resturant.reservation;

import com.example.Resturant.appuser.AppUser;
import com.example.Resturant.appuser.AppUserService;
import com.example.Resturant.order.Order;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final AppUserService appUserService;

    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getReservations() {
        List<Reservation> reservations = reservationService.findAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.findReservation(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        reservation.setReservationNumber(new Random().nextLong());
        reservation.setReservedAt(LocalDateTime.now());
        Reservation newReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation) {
        Reservation updateReservation = reservationService.updateReservation(reservation);
        return new ResponseEntity<>(updateReservation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{reservationId}/appUser/{appUserId}")
    public ResponseEntity<Reservation> addAppUserToOrder(@PathVariable Long reservationId, @PathVariable Long appUserId) {
        Reservation reservation = reservationService.findReservation(reservationId);
        AppUser appUser = appUserService.findAppUser(appUserId);

        appUser.getReservations().add(reservation);
        reservation.setAppUser(appUser);
        reservationService.saveReservation(reservation);

        reservationService.sendVertificationEmail(appUser, reservation.getReservationNumber());

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

}
