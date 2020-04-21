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
import ua.lviv.iot.spring.first.business.SubjectService;
import ua.lviv.iot.spring.first.rest.model.Subject;

@RequestMapping("/subjects")
@RestController
public class SubjectController {

  @Autowired
  private SubjectService subjectService;

  private AtomicInteger idCounter = new AtomicInteger();

  @GetMapping(path = "/{id}")
  public Subject getSubject(@PathVariable("id") Integer subjectId) {
    return subjectService.getSubjectById(subjectId);
  }

  @GetMapping
  public List<Subject> getAllSubjects() {
    return subjectService.getAllSubjects();
  }

  @PostMapping
  public Subject addSubject(final @RequestBody Subject subject) {
    subject.setId(idCounter.incrementAndGet());
    return subjectService.createSubject(subject);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Subject> updateSubject(final @RequestBody Subject subject,
      @PathVariable("id") Integer subjectId) {
    subject.setId(subjectId);
    return subjectService.updateSubject(subject, subjectId);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Subject> deleteSubject(@PathVariable("id") Integer subjectId) {
    return subjectService.deleteSubject(subjectId);
  }

}
