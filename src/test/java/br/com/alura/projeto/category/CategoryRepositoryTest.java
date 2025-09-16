package br.com.alura.projeto.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void existsByCode_shouldReturnTrue_whenCodeExists() {
        Category category = new Category("Programação", "programacao", "#FFFFFF", 1);
        entityManager.persist(category);
        entityManager.flush();

        boolean result = categoryRepository.existsByCode("programacao");

        assertThat(result).isTrue();
    }

    @Test
    void existsByCode_shouldReturnFalse_whenCodeDoesNotExist() {
        boolean result = categoryRepository.existsByCode("inexistente");

        assertThat(result).isFalse();
    }
}
