package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
//make class as a restfull
@RequestMapping(path = "api/v1/student")
//for mapping mehods or anything the at histing time we have to write localhost 8080 api/v1/student
public class StudentController {

    private final StudentService studentService;

    @Autowired
    // this is use for auomatically injection of instances. like injection in constructor
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    //make methods as a end point restfull in a class
    public List<Student> getStudents(){
        return studentService.getStudents();

    }
@PostMapping
    public void registerNewStudent(@RequestBody Student student){
        //@RequestBody just mapping the exist Student and client student and check weather it is present or not already.
        studentService.addNewStudent(student);
    }


    @DeleteMapping(path = "{studentId}")
    public void deletStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email)
    //@RequestParam is requesting for parameter for updating
    {
        studentService.updateStudent(studentId, name, email);
    }

}
