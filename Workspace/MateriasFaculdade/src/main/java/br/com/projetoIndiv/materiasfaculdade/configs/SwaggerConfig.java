package br.com.projetoIndiv.materiasfaculdade.configs;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@SecurityScheme(name="bearer Auth", type=SecuritySchemeType.HTTP, scheme="bearer", bearerFormat="JWT")
public class SwaggerConfig {

	@Value("${prop.swagger.dev-url}")
	private String devUrl;

    @Bean
    public OpenAPI MyOpenAPI(){
		Server server = new Server();
		server.setUrl(devUrl);
		server.setDescription("Server URL for IDE");
		
		Contact contact = new Contact();
		contact.setName("Lucas Cardinot");
		contact.setEmail("lucascardinot2000@gmail.com");
		contact.setUrl("https://www.linkedin.com/in/lucascardinot/");
		
		License license = new License();
		license.setName("Apache License 2.0");
		license.setUrl("https://www.apache.org/licenses/LICENSE-2.0.html");
		
		Info info = new Info();
		info.setTitle("Faculdade & Estudante API");
		info.setDescription("API RESTful para gerenciamento de matérias de faculdade");
		info.setVersion("1.0.0");
		info.setContact(contact);
		info.setLicense(license);
		info.setTermsOfService("https://swagger.io/terms");
		
		return new OpenAPI().info(info).servers(List.of(server));
	}

}