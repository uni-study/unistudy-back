package unistudy.unistudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unistudy.unistudy.DTO.StudygroupDto;
import unistudy.unistudy.domain.Studygroup;
import unistudy.unistudy.service.StudygroupService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class StudygroupController {
    private final StudygroupService studygroupService;

    @Autowired
    public StudygroupController(StudygroupService studygroupService) {
        this.studygroupService = studygroupService;
    }

    // studygroup entity to studygroup dto
    private StudygroupDto convertToDto(Studygroup studygroup) {
        StudygroupDto studygroupDto = new StudygroupDto();
        studygroupDto.setId(studygroup.getId());
        studygroupDto.setLeaderId(studygroup.getLeaderId());
        studygroupDto.setName(studygroup.getName());
        studygroupDto.setDescription(studygroup.getDescription());
        studygroupDto.setDepartment(studygroup.getDepartment());
        studygroupDto.setNumOfPeople(studygroup.getNumOfPeople());
        studygroupDto.setStudyMethod(studygroup.getStudyMethod());
        studygroupDto.setStudyPeriod(studygroup.getStudyPeriod());
        studygroupDto.setRecruitmentDeadline(studygroup.getRecruitmentDeadline());
        studygroupDto.setCurrentState(studygroup.getCurrentState());
        studygroupDto.setContact(studygroup.getContact());

        return studygroupDto;
    }

    // create studygroup
    @PostMapping("/study-group")
    public ResponseEntity<Integer> studygroup(@RequestBody Studygroup studygroup) {
        try {
            Integer studygroupId = studygroupService.createStudygroup(studygroup);
            return new ResponseEntity<>(studygroupId, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // find studygroup by id
    @GetMapping("/study-groups/{id}")
    public ResponseEntity<StudygroupDto> getStudyGroupById(@PathVariable Integer id) {
        Optional<Studygroup> studyGroupOptional = studygroupService.findById(id);

        return studyGroupOptional
                .map(studyGroup -> new ResponseEntity<>(convertToDto(studyGroup), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // delete studygroup by id
    @DeleteMapping("/study-groups/{id}")
    public ResponseEntity<Void> deleteStudyGroup(@PathVariable Integer id) {
        try {
            studygroupService.deleteStudygroup(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // edit studygroup by id
    @PutMapping("/study-groups/{id}")
    public ResponseEntity<StudygroupDto> updateStudyGroup(
            @PathVariable Integer id,
            @RequestBody Studygroup updatedStudygroup
    ) {
        try {
            studygroupService.updateStudygroup(id, updatedStudygroup);
            Optional<Studygroup> updatedStudyGroupOptional = studygroupService.findById(id);

            return updatedStudyGroupOptional
                    .map(studyGroup -> new ResponseEntity<>(convertToDto(studyGroup), HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // find studygroup list and filtering by
    @GetMapping("/study-groups")
    public ResponseEntity<List<StudygroupDto>> getStudyGroups(
            @RequestParam(required = false) Integer leaderId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer currentState,
            @RequestParam(required = false) String contact,
            @RequestParam(required = false) Integer department,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date recruitmentStartDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date recruitmentEndDate
    ) {
        List<Studygroup> studyGroups;

        if (name != null) {
            studyGroups = studygroupService.findByNameContaining(name);
        } else if (description != null) {
            studyGroups = studygroupService.findByDescriptionContaining(description);
        } else if (currentState != null) {
            studyGroups = studygroupService.findByCurrentState(currentState);
        } else if (contact != null) {
            studyGroups = studygroupService.findByContactContaining(contact);
        } else if (department != null) {
            studyGroups = studygroupService.findByDepartment(department);
        } else if (recruitmentStartDate != null && recruitmentEndDate != null) {
            studyGroups = studygroupService.findByRecruitmentDeadlineBetween(recruitmentStartDate, recruitmentEndDate);
        } else if (leaderId != null) {
            studyGroups = studygroupService.findByLeaderId(leaderId);
        } else {
            studyGroups = studygroupService.findAllStudygroup();
        }

        List<StudygroupDto> studygroupDtos = studyGroups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(studygroupDtos, HttpStatus.OK);
    }



}
