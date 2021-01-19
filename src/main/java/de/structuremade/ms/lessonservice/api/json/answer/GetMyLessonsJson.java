package de.structuremade.ms.lessonservice.api.json.answer;

import de.structuremade.ms.lessonservice.api.json.answer.LessonDays.LessonDays;
import de.structuremade.ms.lessonservice.api.json.answer.LessonDays.Times;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetMyLessonsJson {
    private boolean holidays;
    private List<Times> times;
    private List<LessonDays> monday;
    private List<LessonDays> tuesday;
    private List<LessonDays> wednesday;
    private List<LessonDays> thursday;
    private List<LessonDays> friday;

    public GetMyLessonsJson(boolean holidays){
        this.holidays = holidays;
    }
}
