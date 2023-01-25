package com.avismatutina.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@SpringBootApplication(
      scanBasePackages = {"com.avismatutina.kafka"}
)
@EnableAutoConfiguration
public class Application {

   @Bean
   ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }
   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }
}
