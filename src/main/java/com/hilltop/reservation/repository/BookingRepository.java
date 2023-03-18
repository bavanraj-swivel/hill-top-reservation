package com.hilltop.reservation.repository;

import com.hilltop.reservation.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Booking repository
 */
public interface BookingRepository extends JpaRepository<Booking, String> {
}
