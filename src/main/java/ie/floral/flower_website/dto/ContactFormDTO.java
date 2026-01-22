package ie.floral.flower_website.dto;

import lombok.Data;

@Data
public class ContactFormDTO {

    private String name;
    private String email;
    private String phone;
    private String subject;
    private String message;
}
