package promaxject.what_tv;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/postImages/**")
                .addResourceLocations("file:/Users/jun/SpringBoot/what_tv/src/main/resources/static/images/");

        registry.addResourceHandler("/profileImages/**")
                .addResourceLocations("file:/Users/jun/SpringBoot/what_tv/src/main/resources/static/profileImage/");
    }
}
