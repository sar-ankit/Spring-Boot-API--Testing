package com.example.demo.student;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {
@Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return  args -> {

              Student ankit = new Student(

                        "Ankit kumar",
                        "sarankit12@gmail.com",
                        LocalDate.of(1998, JANUARY,12)

              );

            Student ankush = new Student(

                    "Ankush kumar",
                    "sarankit2@gmail.com",
                    LocalDate.of(2000, JANUARY,5)

            );

            Student anjali = new Student(

                    "Anjali kumar",
                    "sarankit42@gmail.com",
                    LocalDate.of(2000, JANUARY,5)

            );
            repository.saveAll(
                    //hibernate is running when we invoke saveAll()
                    List.of(ankit, ankush, anjali)
            );




        };
    }



}
