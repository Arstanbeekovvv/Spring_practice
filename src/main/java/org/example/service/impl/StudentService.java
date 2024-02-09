package org.example.service.impl;

import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.repository.GenericRepository;
import org.example.service.GenericService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService implements GenericService<Student, Long> {
    @Qualifier("studentService")
    private final GenericRepository<Student, Long> studentService;
    public StudentService(GenericRepository<Student, Long> studentService) {
        this.studentService = studentService;
    }

    @Override
    public Student save(Student entity) {
        return studentService.save(entity);
    }

    @Override
    public Student findById(Long aLong) {
        return studentService.findById(aLong);
    }

    @Override
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @Override
    public Student updateById(Long aLong, Student newEntity) {
        return studentService.updateById(aLong, newEntity);
    }

    @Override
    public void deleteById(Long aLong) {
        studentService.deleteById(aLong);
    }
}
