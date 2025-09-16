package br.com.alura.projeto.category;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/categories")
    public String list(Model model) {
        List<CategoryDTO> list = categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::new)
                .toList();

        model.addAttribute("categories", list);

        return "admin/category/list";
    }

    @GetMapping("/admin/category/new")
    public String create(NewCategoryForm newCategory, Model model) {
        return "admin/category/newForm";
    }

    @Transactional
    @PostMapping("/admin/category/new")
    public String save(@Valid NewCategoryForm form, BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return create(form, model);
        }

        if (categoryRepository.existsByCode(form.getCode())) {
            return create(form, model);
        }

        categoryRepository.save(form.toModel());

        redirectAttributes.addFlashAttribute("successMessage", "Categoria cadastrada com sucesso!");
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/category/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));

        model.addAttribute("editCategory", new CategoryDTO(category));

        return "admin/category/update";
    }

    @PostMapping("/admin/category/edit/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("editCategory") CategoryDTO form,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/category/update";
        }

        categoryService.updateCategory(id, form);

        redirectAttributes.addFlashAttribute("successEditMessage", "Categoria atualizada com sucesso!");
        return "redirect:/admin/categories";
    }
}
