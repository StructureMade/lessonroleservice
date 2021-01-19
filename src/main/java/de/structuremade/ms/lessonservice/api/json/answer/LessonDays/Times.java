package de.structuremade.ms.lessonservice.api.json.answer.LessonDays;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Times {
    private String id;
    private String start;
    private String end;
}
