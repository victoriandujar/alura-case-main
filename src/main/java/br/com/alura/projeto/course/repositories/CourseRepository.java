package br.com.alura.projeto.course.repositories;

import br.com.alura.projeto.course.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);
    Optional<Course> findByCode(String code);

    @Query("SELECT c FROM Course c JOIN FETCH c.category")
    List<Course> findAllWithCategory();

    @Query(value = "SELECT c FROM Course c JOIN FETCH c.category",
            countQuery = "SELECT count(c) FROM Course c")
    Page<Course> findAllWithCategory(Pageable pageable);
}
