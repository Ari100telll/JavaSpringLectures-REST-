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
import ua.lviv.iot.spring.first.business.GroupService;
import ua.lviv.iot.spring.first.rest.model.Group;

@RequestMapping("/groups")
@RestController
public class GroupController {

  @Autowired
  private GroupService groupService;

  private AtomicInteger idCounter = new AtomicInteger();

  @GetMapping(path = "/{id}")
  public Group getGroup(@PathVariable("id") Integer groupId) {
    return groupService.getGroupById(groupId);
  }

  @GetMapping
  public List<Group> getAllGroups() {
    return groupService.getAllGroups();
  }

  @PostMapping
  public Group addGroup(final @RequestBody Group group) {
    group.setId(idCounter.incrementAndGet());
    return groupService.createGroup(group);
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Group> updateGroup(final @RequestBody Group group,
      @PathVariable("id") Integer groupId) {
    group.setId(groupId);
    return groupService.updateGroup(group, groupId);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Group> deleteGroup(@PathVariable("id") Integer groupId) {
    return groupService.deleteGroup(groupId);
  }
}
