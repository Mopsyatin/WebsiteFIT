package ie.floral.flower_website.controller;

import ie.floral.flower_website.entity.Category;
import ie.floral.flower_website.entity.Product;
import ie.floral.flower_website.repository.CategoryRepository;
import ie.floral.flower_website.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    @GetMapping("/shop")
    public String shopPage(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "asc") String sort,
            Model model) {

        List<Product> products;
        if ("All".equalsIgnoreCase(category)) {
            products = productService.getProducts(null, sort);
        } else {
            products = productService.getProducts(category, sort);
        }

        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("sortOrder", sort);

        return "shop";
    }
}
