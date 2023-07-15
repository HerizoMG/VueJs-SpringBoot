package l2.projet.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BackendApplication implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins("http://localhost:5173") // Ajoutez l'origine autorisée
				.allowedMethods("GET", "POST", "PUT", "DELETE") // Ajoutez les méthodes HTTP autorisées
				.allowedHeaders("*") // Ajoutez les en-têtes autorisés
				.allowCredentials(true); // Activez la prise en charge des cookies et des en-têtes d'autorisation
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
