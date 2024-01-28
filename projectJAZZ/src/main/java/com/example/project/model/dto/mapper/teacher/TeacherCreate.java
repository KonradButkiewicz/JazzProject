package com.example.project.model.dto.mapper.teacher;

import com.example.project.model.Teacher;
import com.example.project.model.dto.TeacherDTO;

public class TeacherCreate {
    public static Teacher toEntity(TeacherDTO teacherDTO){
        Teacher teacher = new Teacher();

        teacher.setId(teacherDTO.getId());
        teacher.setName(teacherDTO.getName());
        teacher.setLastname(teacherDTO.getLastname());

        return teacher;
    }
}
