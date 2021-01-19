package de.structuremade.ms.lessonservice.api.service;

import de.structuremade.ms.lessonservice.util.Converter;
import de.structuremade.ms.lessonservice.util.JWTUtil;
import de.structuremade.ms.lessonservice.util.database.entity.TimeTableTimes;
import de.structuremade.ms.lessonservice.util.database.repo.SchoolRepo;
import de.structuremade.ms.lessonservice.util.database.repo.TimeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;

@Service
public class TimeService {

    private final Logger LOGGER = LoggerFactory.getLogger(TimeService.class);

    @Autowired
    SchoolRepo schoolRepo;

    @Autowired
    TimeRepo timeRepo;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    Converter converter;

    public int create(String start, String end, String jwt) {
        try {
            LOGGER.info("Check if jwt is expired");
            if (jwtUtil.isTokenExpired(jwt)) {
                LOGGER.info("Token is expired");
                return 3;
            }
            if (schoolRepo.existsById(jwtUtil.extractSpecialClaim(jwt, "schoolid"))) {
                LOGGER.info("Initialize Time");
                TimeTableTimes ttt = new TimeTableTimes();
                ttt.setStarttime(new Time(converter.clockTimeToLong(start)));
                ttt.setEndtime(new Time(converter.clockTimeToLong(end)));
                ttt.setSchool(schoolRepo.getOne(jwtUtil.extractSpecialClaim(jwt, "schoolid")));
                LOGGER.info("Save Time");
                timeRepo.save(ttt);
                return 0;
            } else {
                return 2;
            }
        } catch (Exception e) {
            LOGGER.error("Create Time failed", e.fillInStackTrace());
            return 1;
        }
    }
}
