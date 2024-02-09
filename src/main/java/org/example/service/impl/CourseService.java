package org.example.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.config.HibernateConfig;
import org.example.entity.Course;
import org.example.repository.GenericRepository;
import org.example.repository.impl.CourseRepository;
import org.example.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService implements GenericService<Course, Long> {

    private final GenericRepository<Course, Long> courseService;
    @Autowired
    public CourseService(@Qualifier("courseRepository") GenericRepository courseService) {
        this.courseService = courseService;
    }


    @Override
    public Course save(Course entity) {
        return courseService.save(entity);
    }

    @Override
    public Course findById(Long aLong) {
        return courseService.findById(aLong);
    }

    @Override
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @Override
    public Course updateById(Long aLong, Course newEntity) {
        return courseService.updateById(aLong, newEntity);
    }

    @Override
    public void deleteById(Long aLong) {
        courseService.deleteById(aLong);
    }
}
