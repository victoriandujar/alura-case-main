package br.com.alura.projeto.course.controllers;

import br.com.alura.projeto.category.Category;
import br.com.alura.projeto.category.CategoryService;
import br.com.alura.projeto.course.dtos.CourseDTO;
import br.com.alura.projeto.course.dtos.CourseResponseDTO;
import br.com.alura.projeto.course.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/courses")
public class CourseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    public CourseController(CourseService courseService, CategoryService categoryService) {
        this.courseService = courseService;
        this.categoryService = categoryService;
    }

    // Listagem de cursos
    @GetMapping
    public String list(Model model,
                       RedirectAttributes redirectAttributes) {
        List<CourseResponseDTO> courses = courseService.listCourses();
        model.addAttribute("courses", courses);

        redirectAttributes.addFlashAttribute("successMessage", "Curso cadastrado com sucesso!");
        return "admin/courses/list";
    }

    // Formulário para criar novo curso
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("newCourse", new CourseDTO());

        List<Category> categories = categoryService.listCategories();
        model.addAttribute("categories", categories);

        return "admin/courses/newForm";
    }

    // Salvar novo curso
    @PostMapping("/new")
    public String save(@Valid @ModelAttribute("newCourse") CourseDTO courseDTO,
                       BindingResult result,
                       Model model,
                       RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Se houver erros, devolve categorias para o select
            model.addAttribute("categories", categoryService.listCategories());
            return "admin/courses/newForm";
        }

        try {
            courseService.createCourse(courseDTO);
        } catch (IllegalArgumentException e) {
            // Se houver erro (ex: código duplicado ou categoria inválida)
            result.rejectValue("code", "error.newCourse", e.getMessage());
            model.addAttribute("categories", categoryService.listCategories());
            return "admin/courses/newForm";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Curso cadastrado com sucesso!");
        return "redirect:/admin/courses";
    }

    // Inativar curso
    @PostMapping("/{code}/inactive")
    public String inactivateCourse(@PathVariable String code, RedirectAttributes redirectAttributes) {
        courseService.inactivateCourse(code);

        redirectAttributes.addFlashAttribute("successMessageInactive", "Curso inativado com sucesso!");
        return "redirect:/admin/courses";
    }
}
