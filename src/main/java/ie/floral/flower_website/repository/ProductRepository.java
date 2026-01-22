package ie.floral.flower_website.repository;

import ie.floral.flower_website.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT DISTINCT p FROM Product p JOIN p.categories c " +
            "WHERE (:category IS NULL OR c.name = :category)")
    List<Product> findByCategory(@Param("category") String category, Sort sort);
}
