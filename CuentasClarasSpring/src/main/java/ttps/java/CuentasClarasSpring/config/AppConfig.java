package ttps.java.CuentasClarasSpring.config;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ttps.java.CuentasClarasSpring.services.CategoriaService;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="ttps.java.CuentasClarasSpring.model")
public class AppConfig implements WebMvcConfigurer{
	
	
}
