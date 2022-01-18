package com.example.projektpraktyczny.repository1;

import com.example.projektpraktyczny.model.ApplicationUser;
import com.example.projektpraktyczny.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByClient(ApplicationUser client);
}
