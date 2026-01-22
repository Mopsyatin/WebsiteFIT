package ie.floral.flower_website.service;

import ie.floral.flower_website.entity.Cart;
import ie.floral.flower_website.entity.CartItem;
import ie.floral.flower_website.entity.Product;
import ie.floral.flower_website.entity.User;
import ie.floral.flower_website.repository.CartItemRepository;
import ie.floral.flower_website.repository.CartRepository;
import ie.floral.flower_website.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;


    public Cart getOrCreateCart(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must be logged in to have a cart");
        }

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = Cart.builder().user(user).build();
                    return cartRepository.save(cart);
                });
    }

    public void removeItem(User user, Long itemId) {
        if (user == null) {
            throw new IllegalArgumentException("User must be logged in to have a cart");
        }

        Cart cart = getOrCreateCart(user);

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!item.getCart().getId().equals(cart.getId())) {
            throw new RuntimeException("This item does not belong to the user's cart");
        }

        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            cartItemRepository.save(item);
        } else {
            cart.getItems().remove(item);
            cartItemRepository.delete(item);
        }

    }



    public int getCartItemCount(User user) {
        Cart cart = getOrCreateCart(user);
        return cart.getItems().stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public void clearCart(User user) {
        Cart cart = getOrCreateCart(user);
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
    }

    public void addToCart(User user, Long productId) {
        if (user == null) {
            throw new IllegalArgumentException("User must be logged in to add to cart");
        }

        Cart cart = getOrCreateCart(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = cartItemRepository.findByCartAndProduct(cart, product)
                .map(existingItem -> {
                    existingItem.setQuantity(existingItem.getQuantity() + 1);
                    return existingItem;
                })
                .orElseGet(() -> {
                    CartItem newItem = CartItem.builder()
                            .cart(cart)
                            .product(product)
                            .quantity(1)
                            .build();
                    cart.addItem(newItem);
                    return newItem;
                });

        cartItemRepository.save(item);
    }
}
