package ua.lviv.iot.spring.first.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ua.lviv.iot.spring.first.dataaccess.StudentRepository;
import ua.lviv.iot.spring.first.rest.model.Student;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  public Student createStudent(Student student) {
    return studentRepository.save(student);
  }

  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  public Student getStudentById(Integer studentId) {
    if (studentRepository.existsById(studentId)) {
      return studentRepository.findById(studentId).get();
    }
    return null;
  }

  public ResponseEntity<Student> deleteStudent(Integer studentId) {
    if (studentRepository.existsById(studentId)) {
      studentRepository.deleteById(studentId);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  public ResponseEntity<Student> updateStudent(Student student, Integer studentId) {
    if (studentRepository.existsById(studentId)) {
      return ResponseEntity.ok(studentRepository.saveAndFlush(student));
    }
    return ResponseEntity.notFound().build();
  }

  public List<Student> getAllByFirstName(String firstName) {
    return studentRepository.findAllByFirstName(firstName);
  }
}
