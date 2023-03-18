package com.hilltop.reservation.service;

import com.hilltop.reservation.domain.entity.Booking;
import com.hilltop.reservation.domain.request.BookingRequestDto;
import com.hilltop.reservation.exception.HillTopBookingApplicationException;
import com.hilltop.reservation.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Booking service
 */
@Service
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    /**
     * This method is used to add booking.
     *
     * @param bookingRequestDto bookingRequestDto
     */
    public void addBooking(BookingRequestDto bookingRequestDto) {
        try {
            bookingRepository.save(new Booking(bookingRequestDto));
            log.debug("Successfully added booking data.");
        } catch (DataAccessException e) {
            throw new HillTopBookingApplicationException("Failed to save booking info in database.", e);
        }
    }
}
