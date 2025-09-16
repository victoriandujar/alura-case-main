package br.com.alura.projeto.categories;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void shouldDisplayCategoryList() throws Exception {
        Category category = new Category("Cursos", "CURS1", "#FF0000", 1);
        categoryRepository.save(category);

        mockMvc.perform(get("/admin/categories"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name("admin/category/list"));
    }
}
