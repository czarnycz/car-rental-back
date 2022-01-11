package com.example.projektpraktyczny.repository1;

import com.example.projektpraktyczny.model.CarReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRepository extends JpaRepository<CarReturn, Long> {
}
