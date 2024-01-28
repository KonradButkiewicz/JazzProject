package com.example.project.controller;

import com.example.project.model.Teacher;
import com.example.project.model.dto.TeacherDTO;
import com.example.project.model.dto.mapper.teacher.TeacherRead;
import com.example.project.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TeacherDTO getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.findById(id);
        return TeacherRead.toDTO(teacher);
    }


    @GetMapping("/byCourse/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TeacherDTO> getTeachersByCourse(@PathVariable Long courseId) {
        return teacherService.getTeachersByCourse(courseId);
    }

    @PostMapping("/{courseId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createTeacher(@RequestBody @Validated TeacherDTO teacherDTO, @PathVariable Long courseId) {
        return teacherService.createTeacher(teacherDTO, courseId);
    }

    @PutMapping("/{id}/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public String editTeacher(@RequestBody @Validated TeacherDTO teacherDTO,
                              @PathVariable Long id,
                              @PathVariable Long courseId) {
        Teacher existingTeacher = teacherService.findById(id);
        return teacherService.editTeacher(teacherDTO, existingTeacher, courseId);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteTeacher(@PathVariable Long id) {
        return teacherService.deleteTeacher(id);
    }

}
