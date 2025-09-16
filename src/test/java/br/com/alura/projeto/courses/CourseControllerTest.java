package br.com.alura.projeto.courses;

import br.com.alura.projeto.category.CategoryService;
import br.com.alura.projeto.course.controllers.CourseController;
import br.com.alura.projeto.course.services.CourseService;
import br.com.alura.projeto.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private UserService userService;

    @Test
    void createForm_shouldReturnNewFormViewWithData() throws Exception {
        when(userService.findAllInstructors()).thenReturn(new ArrayList<>());
        when(categoryService.listCategories()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/admin/courses/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/courses/newForm"))
                .andExpect(model().attributeExists("newCourse"))
                .andExpect(model().attributeExists("instructors"))
                .andExpect(model().attributeExists("categories"));
    }
}