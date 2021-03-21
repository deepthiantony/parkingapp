package com.example.parking.util;

import com.example.parking.exception.ParkingApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.Date;

public class DateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    public static double timeDiffInHours(Date startTime, Date endTime) {
        if (ObjectUtils.isEmpty(startTime) || ObjectUtils.isEmpty(endTime)) {
            LOGGER.error("start time {} end time {}", startTime, endTime);
            throw new ParkingApplicationException("Start and end time should not be empty");
        }
        return (endTime.getTime() - startTime.getTime()) / 1000 / 60 / 60;
    }

}
