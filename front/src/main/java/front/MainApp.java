package front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MainApp {
    public static void main(String[] args){

        SpringApplication.run(MainApp.class, args);
    }

    @Bean
    EmbeddedServletContainerCustomizer containerCustomizer() throws Exception {

        return (ConfigurableEmbeddedServletContainer container) -> {

            if (container instanceof TomcatEmbeddedServletContainerFactory) {

                TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
                tomcat.addConnectorCustomizers(
                        (connector) -> {
                            connector.setMaxPostSize(10000000);//10MB
                        }
                );
            }
        };
    }
}
