package ua.lviv.iot.spring.first.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.lviv.iot.spring.first.dataaccess.GroupRepository;
import ua.lviv.iot.spring.first.rest.model.Group;

@Service
public class GroupService {

  @Autowired
  private GroupRepository groupRepository;

  public Group createGroup(Group group) {
    return groupRepository.saveAndFlush(group);
  }

  public List<Group> getAllGroups() {
    return groupRepository.findAll();
  }

  public Group getGroupById(Integer groupId) {
    if (groupRepository.existsById(groupId)) {
      return groupRepository.findById(groupId).get();
    }
    return null;
  }

  public ResponseEntity<Group> deleteGroup(Integer groupId) {
    if (groupRepository.existsById(groupId)) {
      groupRepository.deleteById(groupId);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

  public ResponseEntity<Group> updateGroup(Group group, Integer groupId) {
    if (groupRepository.existsById(groupId)) {
      return ResponseEntity.ok(groupRepository.saveAndFlush(group));
    }
    return ResponseEntity.notFound().build();
  }
}
