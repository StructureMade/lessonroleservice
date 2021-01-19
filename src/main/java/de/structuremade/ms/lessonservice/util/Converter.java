package de.structuremade.ms.lessonservice.util;

import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class Converter {
    public long clockTimeToLong(String clockTime) {
        String[] timeArray = clockTime.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        return calendar.getTimeInMillis();
    }
}
