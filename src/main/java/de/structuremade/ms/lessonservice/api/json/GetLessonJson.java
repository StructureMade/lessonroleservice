package de.structuremade.ms.lessonservice.api.json;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetLessonJson {
    @NotNull(message = "date is needed")
    private String date;
}
