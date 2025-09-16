package br.com.alura.projeto.categories;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryDTO;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class CategoryServiceIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    void shouldUpdateCategory() {
        Category category = new Category("Old Name", "OLD1", "#000000", 1);
        categoryRepository.save(category);

        CategoryDTO dto = new CategoryDTO();
        dto.setName("New Name");
        dto.setCode("NEW1");
        dto.setColor("#FFFFFF");
        dto.setOrder(2);

        categoryService.updateCategory(category.getId(), dto);

        Category updated = categoryRepository.findById(category.getId()).get();
        assertThat(updated.getName()).isEqualTo("New Name");
        assertThat(updated.getCode()).isEqualTo("NEW1");
    }

    @Test
    void shouldThrowWhenCategoryNotFound() {
        CategoryDTO dto = new CategoryDTO();
        dto.setName("New Name");
        dto.setCode("NEW1");
        dto.setColor("#FFFFFF");
        dto.setOrder(2);

        assertThrows(EntityNotFoundException.class, () -> categoryService.updateCategory(999L, dto));
    }
}
