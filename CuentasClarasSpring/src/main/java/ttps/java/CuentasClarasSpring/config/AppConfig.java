package ttps.java.CuentasClarasSpring.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="ttps.java.CuentasClarasSpring")
public class AppConfig implements WebMvcConfigurer{

}
