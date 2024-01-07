package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Student;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultStudentService implements StudentService {

     private final Students students;

     @Autowired
     public DefaultStudentService(Students students) {
          this.students = students;
     }

     @Override
     public Collection<Student> getPassedStudents() {
          // TODO 1 : pass한 학생만 반환하도록 수정하세요.
          // Student 는 Score 를 갖고 있고 Score 에는 pass 여부를 알수 있는 메서드가 있습니다.
          // Java stream api 의 filter() 를 사용하여 필터링된 Student 객체를 리턴 하세요. (Students 와 Student 는 다릅니다.)

          if (students.findAll() == null) {
               throw new NullPointerException("student repository is null");
          }
          return students.findAll().stream()
                  .filter(student ->
                          !student.getScore().isFail())
                  .collect(Collectors.toList());
     }

     @Override
     public Collection<Student> getStudentsOrderByScore() {

          // TODO 4 : 성적 순으로 학생 정보(Student)를 반환합니다.
          // 소팅 문제입니다. Java Stream API 의 소팅 관련 메서드를 사용하세요.
          return DefaultStudentService.this.students.findAll().stream()
                  .sorted((s1, s2) -> s1.getScore().getScore() - s2.getScore().getScore())
                  .collect(Collectors.toList());
     }

}
