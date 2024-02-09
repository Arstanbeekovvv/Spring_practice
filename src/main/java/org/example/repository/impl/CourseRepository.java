package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.example.entity.Course;
import org.example.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository implements GenericRepository<Course, Long> {

    @PersistenceContext
    private final EntityManagerFactory entityManagerFactory;

    public CourseRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Course save(Course entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
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
    public Course findById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = null;
        try {
            entityManager.getTransaction().begin();
            course = entityManager.createQuery("select c from Course c where c.id = :id_course", Course.class).setParameter("id_course", aLong).getSingleResult();
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return course;
    }

    @Override
    public List<Course> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Course> courses = null;
        try {
            entityManager.getTransaction().begin();
            courses = entityManager.createQuery("select c from Course c", Course.class).getResultList();
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return courses;
    }

    @Override
    public Course updateById(Long aLong, Course newEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = null;
        try {
            entityManager.getTransaction().begin();
//            course = entityManager.createQuery("select c from Course c where c.id = :id_course", Course.class).setParameter("id_course", aLong).getSingleResult();
            Course course1 = entityManager.find(Course.class, aLong);
            course1.setName(newEntity.getName());
            course1.setPrice(newEntity.getPrice());
            course1.setDateOfStart(newEntity.getDateOfStart());
            course = course1;
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return course;
    }

    @Override
    public void deleteById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.createQuery("select c from Course c where c.id = :id_course", Course.class).setParameter("id_course", aLong).getSingleResult();
            entityManager.remove(course);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if(entityManager.getTransaction().isActive()){
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }
}
