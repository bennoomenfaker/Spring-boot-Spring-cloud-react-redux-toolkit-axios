package isims.org.companyservice;

import isims.org.companyservice.entities.Company;
import isims.org.companyservice.repositories.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableDiscoveryClient
@SpringBootApplication
public class CompanyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompanyRepository companyRepository) {
		return args -> {
			companyRepository.save(new Company(null, "company1", 1000 + Math.random() * 900));
			companyRepository.save(new Company(null, "company2", 1000 + Math.random() * 900));
			companyRepository.save(new Company(null, "company3", 1000 + Math.random() * 900));
			companyRepository.save(new Company(null, "company4", 1000 + Math.random() * 900));
			companyRepository.save(new Company(null, "company5", 1000 + Math.random() * 900));
		};
	}

	/*@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000")
						.allowedMethods("*");
			}
		};
	}*/
}
