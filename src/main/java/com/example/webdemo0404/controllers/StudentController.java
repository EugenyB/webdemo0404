package com.example.webdemo0404.controllers;

import com.example.webdemo0404.data.Student;
import com.example.webdemo0404.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping("/students")
    public String showStudents(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    @PostMapping("/add_student")
    public String addStudent(
            @RequestParam("student_id") int id,
            @RequestParam("student_name") String name,
            @RequestParam("student_age") int age,
            @RequestParam("student_rating") double rating
    ) {
        Student student = new Student(id, name, age, rating);
        studentService.add(student);
        return "redirect:/students";
    }

    @GetMapping("/delete_student")
    public String deleteStudent(@RequestParam int id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/save_students")
    public String saveStudents() {
        studentService.save();
        return "redirect:/students";
    }
}
