package br.com.alura.projeto.login;

import br.com.alura.projeto.category.CategoryService;
import br.com.alura.projeto.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String loginPage(Model model) {
        return "login"; // seu JSP de login
    }
}
