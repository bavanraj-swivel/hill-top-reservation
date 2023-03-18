package com.hilltop.reservation.exception;

/**
 * HillTop booking application exception
 */
public class HillTopBookingApplicationException extends RuntimeException {

    /**
     * Hill Top application exception with error message and throwable error.
     *
     * @param errorMessage error message
     * @param error        error
     */
    public HillTopBookingApplicationException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

    /**
     * Hill Top application exception with error message.
     *
     * @param errorMessage error message
     */
    public HillTopBookingApplicationException(String errorMessage) {
        super(errorMessage);
    }
}
