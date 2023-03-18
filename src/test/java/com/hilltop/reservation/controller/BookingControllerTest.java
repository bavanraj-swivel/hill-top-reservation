package com.hilltop.reservation.controller;

import com.hilltop.reservation.domain.request.BookingRequestDto;
import com.hilltop.reservation.enumeration.ErrorMessage;
import com.hilltop.reservation.enumeration.SuccessMessage;
import com.hilltop.reservation.exception.HillTopBookingApplicationException;
import com.hilltop.reservation.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Booking controller test
 * Unit tests for {@link  BookingController}
 */
class BookingControllerTest {

    private final String ADD_BOOKING_URI = "/api/booking";
    private final BookingRequestDto bookingRequestDto = getBookingRequestDto();
    @Mock
    private BookingService bookingService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        openMocks(this);
        BookingController bookingController = new BookingController(bookingService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    /**
     * Unit tests for makeBooking() method.
     */
    @Test
    void Should_ReturnOk_When_BookingIsSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ADD_BOOKING_URI)
                        .content(bookingRequestDto.toLogJson())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(SuccessMessage.SUCCESSFULLY_ADDED.getMessage()));
    }

    @Test
    void Should_ReturnBadRequest_When_MissingRequiredFields() throws Exception {
        bookingRequestDto.setUserId(null);
        mockMvc.perform(MockMvcRequestBuilders.post(ADD_BOOKING_URI)
                        .content(bookingRequestDto.toLogJson())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(ErrorMessage.MISSING_REQUIRED_FIELDS.getMessage()))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void Should_ReturnBadRequest_When_InvalidDateFieldsAreGiven() throws Exception {
        bookingRequestDto.setCheckIn(2208969000000L);
        bookingRequestDto.setCheckOut(1893436200000L);
        mockMvc.perform(MockMvcRequestBuilders.post(ADD_BOOKING_URI)
                        .content(bookingRequestDto.toLogJson())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(ErrorMessage.INVALID_DATES.getMessage()))
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void Should_ReturnInternalServerError_When_BookingIsFailedDueToInternalErrors() throws Exception {
        doThrow(new HillTopBookingApplicationException("Failed."))
                .when(bookingService).addBooking(any());
        mockMvc.perform(MockMvcRequestBuilders.post(ADD_BOOKING_URI)
                        .content(bookingRequestDto.toLogJson())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value(ErrorMessage.INTERNAL_SERVER_ERROR.getMessage()))
                .andExpect(jsonPath("$.data").isEmpty());
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