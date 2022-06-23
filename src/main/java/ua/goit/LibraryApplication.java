package ua.goit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class LibraryApplication  extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }
}
