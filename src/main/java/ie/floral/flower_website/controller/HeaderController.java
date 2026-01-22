package ie.floral.flower_website.controller;

import ie.floral.flower_website.entity.Cart;
import ie.floral.flower_website.entity.CartItem;
import ie.floral.flower_website.entity.User;
import ie.floral.flower_website.repository.UserRepository;
import ie.floral.flower_website.service.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
@RequiredArgsConstructor
public class HeaderController {

    private final CartService cartService;
    private final UserRepository userRepository;

    @ModelAttribute("cartItemCount")
    public int cartItemCount(Authentication auth, HttpSession session) {
        if (auth != null) {
            User user = userRepository.findByEmail(auth.getName()).orElse(null);
            if (user == null) return 0;
            return cartService.getCartItemCount(user);
        } else {
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) return 0;
            return cart.getItems().stream()
                    .mapToInt(CartItem::getQuantity)
                    .sum();
        }
    }
}

