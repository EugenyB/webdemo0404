package com.example.webdemo0404.service;

import com.example.webdemo0404.data.Student;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Getter
    private List<Student> students;

    @PostConstruct
    public void init() {
        students = new ArrayList<>(List.of(
                new Student(1, "John", 20, 75),
                new Student(2, "Bill", 21, 99),
                new Student(3, "Kate", 24, 95),
                new Student(4, "Donald", 18, 76),
                new Student(5, "Jane", 20, 85)
        ));
    }

    public void add(Student student) {
        students.add(student);
    }

    public void delete(Student student) {
        students.remove(student);
    }

    public void deleteById(int id) {
        students.removeIf(s -> s.getId() == id);
    }

    public void save() {
        try (PrintWriter out = new PrintWriter("students.txt")) {
            for (Student s : students) {
                out.println(s.getId() + ";" + s.getName() + ";" + s.getAge() + ";" + s.getRating());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
