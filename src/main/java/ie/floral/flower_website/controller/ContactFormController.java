package ie.floral.flower_website.controller;

import ie.floral.flower_website.dto.ContactFormDTO;
import ie.floral.flower_website.service.EmailSendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ContactFormController {

    private final EmailSendService emailService;

    @PostMapping("/contact")
    public String handleContactForm(
            @ModelAttribute ContactFormDTO contactForm,
            Model model
    ) {
        try {
            String body = """
                    This is a test message, in the future, an email can be sent to the fixed email!
                    Name: %s
                    Email: %s
                    Phone: %s

                    %s
                    """.formatted(
                    contactForm.getName(),
                    contactForm.getEmail(),
                    contactForm.getPhone(),
                    contactForm.getMessage()
            );

            emailService.sendContactEmail(
                    contactForm.getEmail(),
                    contactForm.getSubject(),
                    body
            );

            model.addAttribute("success", "Your message has been sent!");
        } catch (MailException e) {
            log.error("Failed to send contact email", e);
            model.addAttribute("error", "Something went wrong, try again!");
        }

        return "contact";
    }
}
