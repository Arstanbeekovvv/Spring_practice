package org.example;

import jakarta.persistence.PersistenceContext;
import org.example.config.HibernateConfig;
import org.example.entity.Course;
import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.service.GenericService;
import org.example.service.impl.CourseService;
import org.example.service.impl.LessonService;
import org.example.service.impl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        System.out.println("Hello World!");
        ApplicationContext services = new AnnotationConfigApplicationContext(HibernateConfig.class);
        CourseService courseService = services.getBean(CourseService.class);
        LessonService lessonService = services.getBean(LessonService.class);
        StudentService studentService = services.getBean(StudentService.class);

        //save
//        System.out.println(courseService.save(new Course("Java", 14000, LocalDate.of(2023, 10, 1))));
//        System.out.println(lessonService.save(new Lesson()));
//        System.out.println(studentService.save(new Student()));

        //find by id
//        System.out.println(courseService.findById(1L));
//        System.out.println(lessonService.findById(1L));
//        System.out.println(studentService.findById(1L));

        //get all
//        System.out.println(courseService.getAll());
//        System.out.println(lessonService.getAll());
//        System.out.println(studentService.getAll());

        //update
//        System.out.println(courseService.updateById(1L, new Course()));
//        System.out.println(lessonService.updateById(1L, new Lesson()));
//        System.out.println(studentService.updateById(1L, new Student()));

        //delete
        courseService.deleteById(1L);
        lessonService.deleteById(1L);
        studentService.deleteById(1L);
    }
}
