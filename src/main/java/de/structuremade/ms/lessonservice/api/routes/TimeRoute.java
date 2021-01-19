package de.structuremade.ms.lessonservice.api.routes;

import de.structuremade.ms.lessonservice.api.json.CreateTimeJson;
import de.structuremade.ms.lessonservice.api.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("service/time")
public class TimeRoute {

    @Autowired
    TimeService timeService;

    @PostMapping("/create")
    public void createTime(@RequestBody CreateTimeJson timeJson, HttpServletRequest request, HttpServletResponse response) {
        switch (timeService.create(timeJson.getStart(), timeJson.getEnd(), request.getHeader("Authorization").substring(7))) {
            case 0 -> response.setStatus(HttpStatus.CREATED.value());
            case 1 -> response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            case 2 -> response.setStatus(HttpStatus.BAD_REQUEST.value());
            case 3 -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }
}
