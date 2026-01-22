package ie.floral.flower_website.controller;

import ie.floral.flower_website.entity.Cart;
import ie.floral.flower_website.repository.CartRepository;
import ie.floral.flower_website.security.CustomUserDetails;
import ie.floral.flower_website.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import ie.floral.flower_website.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartRepository cartRepository;

    @GetMapping("/cart")
    public String viewCart(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        User user = userDetails.getUser();
        Cart cart = cartService.getOrCreateCart(user);
        model.addAttribute("cart", cart);

        BigDecimal subtotal = cart.getItems().stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal delivery = BigDecimal.valueOf(4.3); // например
        BigDecimal total = subtotal.add(delivery);

        model.addAttribute("subtotal", subtotal);
        model.addAttribute("delivery", delivery);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/cart/remove/{itemId}")
    public String removeItem(@PathVariable Long itemId,
                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        cartService.removeItem(userDetails.getUser(), itemId);
        return "redirect:/cart";
    }


    @PostMapping("/cart/clear")
    public String clearCart(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login"; // если не залогинен
        }

        cartService.clearCart(userDetails.getUser());
        return "redirect:/cart";
    }
    @PostMapping("/cart/checkout")
    public String checkout(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        User user = userDetails.getUser();
        Cart cart = cartService.getOrCreateCart(user);

        if (cart.getItems().isEmpty()) {
            model.addAttribute("message", "Your cart is empty. Add items before checkout.");
            return "cart";
        }


        model.addAttribute("cart", cart);
        model.addAttribute("message", "Checkout successful! (Demo)");


        cartService.clearCart(user);


        return "redirect:/shop";
    }



}
