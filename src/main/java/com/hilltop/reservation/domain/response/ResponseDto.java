package com.hilltop.reservation.domain.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilltop.reservation.exception.HillTopBookingApplicationException;

/**
 * ResponseDto
 */
public interface ResponseDto {

    /**
     * This method converts object to json string.
     *
     * @return json string
     */
    default String toLogJson() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new HillTopBookingApplicationException("Object to json conversion was failed.", e);
        }
    }
}
