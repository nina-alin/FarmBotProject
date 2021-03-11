package com.rostand.FarmBotWEB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FarmBotWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmBotWebApplication.class, args);
	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
}

/*

https://www.baeldung.com/jpa-one-to-one
https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-one-mapping-example/
https://www.baeldung.com/rest-with-spring-series
https://www.baeldung.com/spring-boot
https://www.baeldung.com/hibernate-one-to-many
https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
https://spring.io/guides/gs/reactive-rest-service/
https://spring.io/guides/gs/scheduling-tasks/
 */