package com.apap.tutorial6.repository;

import java.util.Optional;

import com.apap.tutorial6.model.FlightModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FlightDb
 */
@Repository
public interface FlightDb extends JpaRepository<FlightModel, Long> {
    void deleteByFlightNumber(String flightNumber);
    FlightModel findById(long Id);
    Optional<FlightModel> findByFlightNumber(String flightNumber);
}