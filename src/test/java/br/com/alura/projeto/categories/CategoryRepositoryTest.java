package br.com.alura.projeto.categories;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void shouldSaveAndFindCategory() {
        Category category = new Category("Cursos", "CURS1", "#FF0000", 1);
        categoryRepository.save(category);

        List<Category> categories = categoryRepository.findAll();

        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getName()).isEqualTo("Cursos");
    }

    @Test
    void shouldCheckExistsByCode() {
        Category category = new Category("Cursos", "CURS1", "#FF0000", 1);
        categoryRepository.save(category);

        boolean exists = categoryRepository.existsByCode("CURS1");
        assertThat(exists).isTrue();
    }
}
