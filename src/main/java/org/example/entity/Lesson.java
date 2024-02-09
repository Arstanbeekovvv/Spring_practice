package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_gen")
    @SequenceGenerator(name = "lesson_gen", sequenceName = "lesson_seq", allocationSize = 1)
    private Long id;

    private String title;

    private String description;

    @Column(name = "video_link")
    private String videoLink;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @Column(name = "is_presentation")
   private boolean isPresentation;


}
