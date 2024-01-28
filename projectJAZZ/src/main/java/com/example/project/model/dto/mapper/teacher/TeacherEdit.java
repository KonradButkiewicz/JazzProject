package com.example.project.model.dto.mapper.teacher;

import com.example.project.model.Teacher;
import com.example.project.model.dto.TeacherDTO;

public class TeacherEdit {
    public static Teacher toEntity(Teacher teacher, TeacherDTO teacherDTO){
        teacher.setId(teacherDTO.getId());
        teacher.setName(teacherDTO.getName());
        teacher.setLastname(teacherDTO.getLastname());

        return teacher;
    }
}
