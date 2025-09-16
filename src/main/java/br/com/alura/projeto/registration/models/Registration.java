package br.com.alura.projeto.registration.models;

import br.com.alura.projeto.course.models.Course;
import br.com.alura.projeto.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"}))
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    public Registration() {}

    public Registration(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    @PrePersist
    protected void onCreate() {
        this.registrationDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Course getCourse() { return course; }
    public LocalDateTime getRegistrationDate() { return registrationDate; }
}