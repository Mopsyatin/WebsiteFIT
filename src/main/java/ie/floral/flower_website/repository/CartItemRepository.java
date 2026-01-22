package ie.floral.flower_website.repository;

import ie.floral.flower_website.entity.Cart;
import ie.floral.flower_website.entity.CartItem;
import ie.floral.flower_website.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}