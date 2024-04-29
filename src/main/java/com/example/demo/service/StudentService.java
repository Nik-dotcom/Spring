package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    @Autowired
    public StudentService(StudentRepo studentRepo){ this.studentRepo = studentRepo;}

    public void newStudent(Student student){ studentRepo.save(student);}

    public List<Student> allStudents() { return studentRepo.findAll();}

    public void deleteStudent(Long id){ studentRepo.deleteById(id);}

    public void updateStudent(Long id, Student updatedStudent){
        Student student = studentRepo.findById(id).orElseThrow -> new IllegalArgumentException("Invalid student id" + id);
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.getAge(updatedStudent.getAge());
        studentRepo.save(student);
    }

    public Student getStudentById(Long id){
        return studentRepo.findById()
                .orElseThrow(() -> new RuntimeException("Стуцдент с укзанным идентификатолром не найден:" + id));
    }

}
