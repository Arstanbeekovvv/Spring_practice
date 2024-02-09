package org.example.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.example.entity.Lesson;
import org.example.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LessonRepository implements GenericRepository<Lesson, Long> {
    @PersistenceContext
    private final EntityManager entityManager;
    public LessonRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Lesson save(Lesson entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.close();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return entity;
    }

    @Override
    public Lesson findById(Long aLong) {
        Lesson lesson = null;
        try {
            entityManager.getTransaction().begin();
            lesson = entityManager.find(Lesson.class, aLong);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.close();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return lesson;
    }

    @Override
    public List<Lesson> getAll() {
        List<Lesson> lessons = null;
        try {
            entityManager.getTransaction().begin();
            lessons = entityManager.createQuery("select l from Lesson l",Lesson.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.close();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return lessons;
    }

    @Override
    public Lesson updateById(Long aLong, Lesson newEntity) {
        Lesson lesson = null;
        try {
            entityManager.getTransaction().begin();
            lesson = entityManager.find(Lesson.class, aLong);
            lesson.setTitle(newEntity.getTitle());
            lesson.setDescription(newEntity.getDescription());
            lesson.setPresentation(newEntity.isPresentation());
            lesson.setVideoLink(newEntity.getVideoLink());
            lesson.setPublishedDate(newEntity.getPublishedDate());
            entityManager.merge(lesson);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.close();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return lesson;
    }

    @Override
    public void deleteById(Long aLong) {
        try {
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, aLong);
            entityManager.remove(lesson);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            if(entityManager.getTransaction().isActive()){
                entityManager.close();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }
}
