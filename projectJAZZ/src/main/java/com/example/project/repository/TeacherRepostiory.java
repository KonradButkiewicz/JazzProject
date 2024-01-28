package com.example.project.repository;

import com.example.project.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepostiory extends JpaRepository<Teacher, Long> {
    List<Teacher> findByLastname(String lastname);

    List<Teacher> findByCourse_Id(Long courseId);
    void deleteById(Long id);

}
