package com.hilltop.reservation.service;

import com.hilltop.reservation.domain.request.BookingRequestDto;
import com.hilltop.reservation.exception.HillTopBookingApplicationException;
import com.hilltop.reservation.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

/**
 * Booking service test
 * Unit tests for {@link  BookingService}
 */
class BookingServiceTest {

    private final BookingRequestDto bookingRequestDto = getBookingRequestDto();
    @Mock
    private BookingRepository bookingRepository;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        openMocks(this);
        bookingService = new BookingService(bookingRepository);
    }

    /**
     * Unit tests for addBooking() method.
     */
    @Test
    void Should_SaveBookingDetailOnDatabase_When_ValidDataIsGiven() {
        bookingService.addBooking(bookingRequestDto);
        verify(bookingRepository, times(1)).save(any());
    }

    @Test
    void Should_ThrowHillTopBookingApplicationException_When_FailedToAddBookingData() {
        when(bookingRepository.save(any())).thenThrow(new DataAccessException("Failed.") {
        });
        HillTopBookingApplicationException exception = assertThrows(HillTopBookingApplicationException.class,
                () -> bookingService.addBooking(bookingRequestDto));
        assertEquals("Failed to save booking info in database.", exception.getMessage());
    }


    /**
     * This method is used to mock bookingRequestDto.
     *
     * @return bookingRequestDto
     */
    private BookingRequestDto getBookingRequestDto() {
        BookingRequestDto bookingRequestDto = new BookingRequestDto();
        bookingRequestDto.setUserId("uid-123");
        bookingRequestDto.setRoomId("rid-123");
        bookingRequestDto.setCustomerCount(4);
        bookingRequestDto.setAmount(1000);
        bookingRequestDto.setCheckIn(1893436200000L);
        bookingRequestDto.setCheckOut(2208969000000L);
        return bookingRequestDto;
    }
}