package br.com.alura.projeto.courses;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryRepository;
import br.com.alura.projeto.user.User;
import br.com.alura.projeto.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldDisplayCourseList() throws Exception {
        Category category = new Category("Cursos", "CURS1", "#FF0000", 1);
        categoryRepository.save(category);

        User instructor = new User();
        instructor.setName("Prof Teste");
        instructor.setEmail("prof@test.com");
        userRepository.save(instructor);

        mockMvc.perform(get("/admin/courses"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("coursePage"))
                .andExpect(view().name("admin/courses/list"));
    }
}
