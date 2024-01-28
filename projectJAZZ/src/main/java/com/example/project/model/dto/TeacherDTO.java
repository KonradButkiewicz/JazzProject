package com.example.project.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TeacherDTO {
    private Long id;

    @NotBlank(message = "Name can not be null")
    private String name;

    @NotBlank(message = "Last name can not be null")
    private String lastname;

    @JsonProperty("course")
    private CourseDTO courseDTO;
}
