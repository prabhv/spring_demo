package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student miriam = new Student(
                    "Miriam",
                    LocalDate.of(2000, 11, 11),
                    "email@email.com");

            Student jamal = new Student(
                    "Jamal",
                    LocalDate.of(2003, 9, 11),
                    "jamal@email.com");

            repository.saveAll(
                    List.of(miriam, jamal)
            );
        };
    }
}
