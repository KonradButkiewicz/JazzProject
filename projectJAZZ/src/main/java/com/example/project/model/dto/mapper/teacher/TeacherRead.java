package com.example.project.model.dto.mapper.teacher;

import com.example.project.model.Teacher;
import com.example.project.model.dto.StudentDTO;
import com.example.project.model.dto.TeacherDTO;
import com.example.project.model.dto.mapper.course.CourseRead;

public class TeacherRead {
    public static TeacherDTO toDTO(Teacher teacher){
        TeacherDTO teacherDTO = new TeacherDTO();

        teacherDTO.setId(teacher.getId());
        teacherDTO.setName(teacher.getName());
        teacherDTO.setLastname(teacher.getLastname());
        teacherDTO.setCourseDTO(CourseRead.toDTO(teacher.getCourse()));

        return teacherDTO;
    }
}
