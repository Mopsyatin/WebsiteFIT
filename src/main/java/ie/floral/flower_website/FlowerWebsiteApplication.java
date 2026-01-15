package ie.floral.flower_website;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlowerWebsiteApplication {

    private static final Logger log = LoggerFactory.getLogger(FlowerWebsiteApplication.class);


    public static void main(String[] args) {
		SpringApplication.run(FlowerWebsiteApplication.class, args);
	}


}
