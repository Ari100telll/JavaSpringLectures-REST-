package ua.lviv.iot.spring.first.rest.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.spring.first.business.StudentService;
import ua.lviv.iot.spring.first.rest.model.Student;

@RequestMapping("/students")
@RestController
public class StudentController {

  @Autowired
  private StudentService studentService;

  private AtomicInteger idCounter = new AtomicInteger();

  @GetMapping(path = "/{id}")
  public Student getStudent(@PathVariable("id") Integer studentId) {
    return studentService.getStudentById(studentId);
  }

  @GetMapping
  public List<Student> getAllStudents() {
    return studentService.getAllStudents();
  }

  @PostMapping
  public Student addStudent(final @RequestBody Student student) {
    student.setId(idCounter.incrementAndGet());
    return studentService.createStudent(student);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Student> updateStudent(final @RequestBody Student student,
      @PathVariable("id") Integer studentId) {
    student.setId(studentId);
    return studentService.updateStudent(student, studentId);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer studentId) {
    return studentService.deleteStudent(studentId);
  }

}
