package ie.floral.flower_website.controller;

import ie.floral.flower_website.entity.User;
import ie.floral.flower_website.security.CustomUserDetails;
import ie.floral.flower_website.service.CartService;
import ie.floral.flower_website.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CartAddController {

    private final CartService cartService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable Long productId,
                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        cartService.addToCart(userDetails.getUser(), productId);
        return "redirect:/shop";
    }

}





