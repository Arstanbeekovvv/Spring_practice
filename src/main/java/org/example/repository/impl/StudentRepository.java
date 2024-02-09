package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository implements GenericRepository<Student, Long> {
    @PersistenceContext
    private final EntityManager entityManager;

    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Student save(Student entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return entity;
    }

    @Override
    public Student findById(Long aLong) {
        Student student = null;
        try {
            entityManager.getTransaction().begin();
            student = entityManager.find(Student.class, aLong);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = null;
        try {
            entityManager.getTransaction().begin();
            students = entityManager.createQuery("select s from Student s", Student.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return students;
    }

    @Override
    public Student updateById(Long aLong, Student newEntity) {
        Student student = null;
        try {
            entityManager.getTransaction().begin();
            student = entityManager.find(Student.class, aLong);
            student.setName(newEntity.getName());
            student.setEmail(newEntity.getEmail());
            student.setYearOfBirth(newEntity.getYearOfBirth());
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return student;
    }

    @Override
    public void deleteById(Long aLong) {
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, aLong);
            entityManager.remove(student);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }
}
