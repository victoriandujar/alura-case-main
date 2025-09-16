package br.com.alura.projeto.login;

import br.com.alura.projeto.category.CategoryService;
import br.com.alura.projeto.category.dtos.CategoryWithCoursesDTO;
import br.com.alura.projeto.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {
    private final CategoryService categoryService;
    public LoginController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        List<CategoryWithCoursesDTO> categoriesWithCourses = categoryService.getCategoriesWithCourses();

        model.addAttribute("reportData", categoriesWithCourses);
        return "login";
    }
}
