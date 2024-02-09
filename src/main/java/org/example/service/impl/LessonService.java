package org.example.service.impl;

import org.example.entity.Lesson;
import org.example.repository.GenericRepository;
import org.example.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LessonService implements GenericService<Lesson, Long> {
    @Qualifier("lessonRepository")
    private final GenericRepository<Lesson, Long> lessonRepository;
    public LessonService(GenericRepository<Lesson, Long> lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Lesson save(Lesson entity) {
        return lessonRepository.save(entity);
    }

    @Override
    public Lesson findById(Long aLong) {
        return lessonRepository.findById(aLong);
    }

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.getAll();
    }

    @Override
    public Lesson updateById(Long aLong, Lesson newEntity) {
        return lessonRepository.updateById(aLong, newEntity);
    }

    @Override
    public void deleteById(Long aLong) {
        lessonRepository.deleteById(aLong);
    }
}
