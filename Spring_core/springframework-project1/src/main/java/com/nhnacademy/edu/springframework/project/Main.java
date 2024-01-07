package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.service.Student;
import java.util.Collection;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

     // TODO 9 - 성공적으로 실행되어야 합니다.
     public static void main(String[] args) {

          AnnotationConfigApplicationContext context =
                  new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project");

          DataLoadService dataLoadService = context.getBean("csvDataLoadService", DataLoadService.class);
          dataLoadService.loadAndMerge();

          StudentService studentService = context.getBean("defaultStudentService", DefaultStudentService.class);
          Collection<Student> passedStudents = studentService.getPassedStudents();
          System.out.println(passedStudents);


          Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
          System.out.println(orderedStudents);
     }
}
