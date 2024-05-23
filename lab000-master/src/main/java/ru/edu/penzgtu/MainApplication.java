package ru.edu.penzgtu;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@OpenAPIDefinition(
        info = @Info(
                title = "PenzGTU Java Lab API",
                description = "API for labs", version = "1.0.0",
                contact = @Contact(
                        name = "Nikita Dudchenko",
                        email = "qwertyuiop@gmail.com"
                )
        )

)
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }


}
