package co.com.crediYa.bancolombia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"co.com.crediYa", "co.com.bancolombia"})
@ConfigurationPropertiesScan(basePackages = {"co.com.crediYa", "co.com.bancolombia"})
@EnableJpaRepositories(basePackages = {"co.com.bancolombia.jpa"})
@EntityScan(basePackages = {"co.com.bancolombia.jpa"})
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}