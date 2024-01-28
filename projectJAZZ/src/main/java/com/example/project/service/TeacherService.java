package com.example.project.service;

import com.example.project.model.Course;
import com.example.project.model.Teacher;
import com.example.project.model.dto.TeacherDTO;
import com.example.project.model.dto.mapper.teacher.TeacherCreate;
import com.example.project.model.dto.mapper.teacher.TeacherEdit;
import com.example.project.model.dto.mapper.teacher.TeacherRead;
import com.example.project.model.exceptions.global.CreationException;
import com.example.project.model.exceptions.global.NotAllowedOperationException;
import com.example.project.model.exceptions.service.TeacherNotFoundException;
import com.example.project.repository.CourseRepository;
import com.example.project.repository.TeacherRepostiory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepostiory teacherRepostiory;
    private final CourseRepository courseRepository;

    @Transactional
    public String createTeacher(TeacherDTO teacherDTO, Long courseId) {
        try {
            Teacher teacher = TeacherCreate.toEntity(teacherDTO);

            Course course = courseRepository.findById(courseId).orElseThrow(() ->
                    new TeacherNotFoundException("Course not found with ID: " + courseId));

            teacher.setCourse(course);

            teacherRepostiory.save(teacher);

            return "Teacher created";
        } catch (Exception e) {
            throw new CreationException("Error creating teacher: " + e.getMessage());
        }
    }

    @Transactional
    public String editTeacher(TeacherDTO teacherDTO, Teacher teacher, Long courseId) {
        try {
            teacher = TeacherEdit.toEntity(teacher, teacherDTO);
            teacher.setCourse(courseRepository.findById(courseId).orElseThrow(() ->
                    new TeacherNotFoundException("Course not found with ID: " + courseId)));
            teacherRepostiory.save(teacher);

            return "Teacher edited";
        } catch (Exception e) {
            throw new RuntimeException("Edit failed", e);
        }
    }

    public List<TeacherDTO> getAllTeachers() {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        teacherRepostiory.findAll().forEach(t -> teacherDTOList.add(TeacherRead.toDTO(t)));

        return teacherDTOList;
    }

    public List<TeacherDTO> getTeachersByCourse(Long courseId) {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        teacherRepostiory.findByCourse_Id(courseId).forEach(t -> teacherDTOList.add(TeacherRead.toDTO(t)));

        return teacherDTOList;
    }


    public Teacher findById(Long teacherId) {
        return teacherRepostiory.findById(teacherId)
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with ID: " + teacherId));
    }

    @Transactional
    public String deleteTeacher(Long teacherId) {
        if (teacherId < 0) {
            throw new NotAllowedOperationException();
        }

        Teacher teacher = teacherRepostiory.findById(teacherId).orElseThrow(() ->
                new TeacherNotFoundException("Teacher not found with ID: " + teacherId));

        teacherRepostiory.delete(teacher);

        return "Teacher deleted";
    }

    public List<TeacherDTO> findByLastname(String lastname) {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        teacherRepostiory.findByLastname(lastname).forEach(t -> teacherDTOList.add(TeacherRead.toDTO(t)));

        return teacherDTOList;
    }
}
