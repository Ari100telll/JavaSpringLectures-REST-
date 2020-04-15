package ua.lviv.iot.spring.first.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ua.lviv.iot.spring.first.dataaccess.SubjectRepository;
import ua.lviv.iot.spring.first.rest.model.Subject;

@Service
public class SubjectService {

  @Autowired
  private SubjectRepository subjectRepository;

  public Subject createSubject(Subject subject) {
    return subjectRepository.save(subject);
  }

  public List<Subject> getAllSubjects() {
    return subjectRepository.findAll();
  }

  public Subject getSubjectById(Integer subjectId) {
    if (subjectRepository.existsById(subjectId)) {
      return subjectRepository.findById(subjectId).get();
    }
    return null;
  }

  public ResponseEntity<Subject> deleteSubject(Integer subjectId) {
    if (subjectRepository.existsById(subjectId)) {
      subjectRepository.deleteById(subjectId);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  public ResponseEntity<Subject> updateSubject(Subject subject, Integer subjectId) {
    if (subjectRepository.existsById(subjectId)) {
      return ResponseEntity.ok(subjectRepository.saveAndFlush(subject));
    }
    return ResponseEntity.notFound().build();
  }

}
