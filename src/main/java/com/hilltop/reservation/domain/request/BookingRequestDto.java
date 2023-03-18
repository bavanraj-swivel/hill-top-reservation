package com.hilltop.reservation.domain.request;

import lombok.Getter;
import lombok.Setter;

/**
 * Booking requestDto
 */
@Getter
@Setter
public class BookingRequestDto implements RequestDto {

    private String userId;
    private String roomId;
    private int customerCount;
    private double amount;
    private long checkIn;
    private long checkOut;

    /**
     * Used to validate required fields.
     *
     * @return true/false
     */
    @Override
    public boolean isRequiredFieldsAvailable() {
        return isNonEmpty(userId) && isNonEmpty(roomId) && customerCount > 0 && amount > 0;
    }

    /**
     * This method is to validate checkIn & checkOut dates.
     *
     * @return true/false
     */
    public boolean isValidDate() {
        long currentTimeStamp = System.currentTimeMillis();
        return checkIn > currentTimeStamp && checkOut > checkIn;
    }
}
