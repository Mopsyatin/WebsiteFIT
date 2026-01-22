package ie.floral.flower_website.service;

import ie.floral.flower_website.entity.Product;
import ie.floral.flower_website.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getProducts(String category, String sort) {
        Sort sorting = "desc".equalsIgnoreCase(sort)
                ? Sort.by("price").descending()
                : Sort.by("price").ascending();

        return repository.findByCategory(category, sorting);
    }
}
