package br.com.alura.projeto.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCode(String code);

    @Query("SELECT DISTINCT cat FROM Category cat LEFT JOIN FETCH cat.courses")
    List<Category> findAllWithCourses();
}
