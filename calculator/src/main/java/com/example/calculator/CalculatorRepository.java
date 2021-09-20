package com.example.calculator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CalculatorRepository extends JpaRepository<Calculator, Double> {
    @Query("SELECT c FROM Calculator c WHERE c.id=?1")
    Optional<Calculator> findCalculationById(Long id);
}
