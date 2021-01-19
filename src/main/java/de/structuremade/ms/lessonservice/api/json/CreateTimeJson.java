package de.structuremade.ms.lessonservice.api.json;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateTimeJson {
    @NotNull
    private String start;
    @NotNull
    private String end;
}
