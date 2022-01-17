package com.example.projektpraktyczny.repository1;

import com.example.projektpraktyczny.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentDtoRepository extends JpaRepository<Rent, Long> {
}
