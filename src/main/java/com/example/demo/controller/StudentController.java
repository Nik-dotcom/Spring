package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {this.studentService = studentService;}

    @GetMapping("/newStudent")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student";
    }

    @PostMapping("/newStudent")
    public String registerUser(@ModelAttribute("student") Student student) {
        studentService.newStudent(student);
    }

    @GetMapping
    public String getAllStudents(Model model){
        model.addAttribute("students", studentService.allStudents());
        return "studentsList";
    }

    @PostMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("/updateStudent/{id}")
    public String updatedStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student student){
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }
}
