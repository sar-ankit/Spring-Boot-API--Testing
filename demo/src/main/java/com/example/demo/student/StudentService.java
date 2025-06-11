package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
//make a class as a service class, @Component, @service, @Bean all function are same but he service work specifically
public class StudentService {

    private final StudentRepository studentRepository;
@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
    return  studentRepository.findAll();


//        return List.of(
//                new Student(
//                        1L,
//                        "Annnkit kumar",
//                        "sarankit@gmail.com",
//                        LocalDate.of(2000, Month.JANUARY,12),
//                        21
//
//
//                )
//        );
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional =   studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw  new IllegalStateException("email taken");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) {
    studentRepository.findById(studentId);
    boolean exist = studentRepository.existsById(studentId);
    if(!exist){
        throw new IllegalStateException("student with id " + studentId + " does not exist");
    }
    studentRepository.deleteById(studentId);
    }
     @Transactional
    public void updateStudent(Long studentId, String name, String email) {
    Student student = studentRepository.findById(studentId)
            .orElseThrow(()-> new IllegalStateException("Student with id " + studentId + " does not exist "));

            if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
                student.setName(name);

            }

            if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
                Optional<Student> studentOptional;
             studentOptional = studentRepository
                     .findStudentByEmail(email);

             if(studentOptional.isPresent()){
                    throw new IllegalStateException("email taken");
                }
                student.setEmail(email);
         }
    }
}
