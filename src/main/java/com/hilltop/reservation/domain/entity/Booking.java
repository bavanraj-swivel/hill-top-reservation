package com.hilltop.reservation.domain.entity;

import com.hilltop.reservation.domain.request.BookingRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.UUID;

/**
 * Booking entity
 */
@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    @Transient
    private static final String BOOKING_ID_PREFIX = "bid-";

    @Id
    private String id;
    private String userId;
    private String roomId;
    private int customerCount;
    private double amount;
    private long checkIn;
    private long checkOut;

    public Booking(BookingRequestDto bookingRequestDto) {
        this.id = BOOKING_ID_PREFIX + UUID.randomUUID();
        this.userId = bookingRequestDto.getUserId();
        this.roomId = bookingRequestDto.getRoomId();
        this.customerCount = bookingRequestDto.getCustomerCount();
        this.amount = bookingRequestDto.getAmount();
        this.checkIn = bookingRequestDto.getCheckIn();
        this.checkOut = bookingRequestDto.getCheckOut();
    }
}
