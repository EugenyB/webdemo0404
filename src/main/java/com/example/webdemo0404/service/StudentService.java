package com.example.webdemo0404.service;

import com.example.webdemo0404.data.Student;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Getter
    private List<Student> students;

    @PostConstruct
    public void init() {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("students.txt"))) {
            students = new ArrayList<>(reader.lines().map(line -> {
                String[] s = line.split(";");
                int id = Integer.parseInt(s[0]);
                String name = s[1];
                int age = Integer.parseInt(s[2]);
                double rating = Double.parseDouble(s[3]);
                return new Student(id, name, age, rating);
            }).toList());
        } catch (IOException e) {
            students = new ArrayList<>();
        }
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
