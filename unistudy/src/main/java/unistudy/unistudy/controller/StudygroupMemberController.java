package unistudy.unistudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unistudy.unistudy.DTO.StudygroupMemberDto;
import unistudy.unistudy.domain.Studygroup;
import unistudy.unistudy.domain.StudygroupMember;
import unistudy.unistudy.service.StudygroupMemberService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudygroupMemberController {

    private final StudygroupMemberService studygroupMemberService;

    @Autowired
    public StudygroupMemberController(StudygroupMemberService studygroupMemberService) {
        this.studygroupMemberService = studygroupMemberService;
    }

    // find studygroup-member relationship by id
    @GetMapping("/studygroup-member/{id}")
    public ResponseEntity<StudygroupMemberDto> getMemberById(@PathVariable Integer id) {
        StudygroupMember member = studygroupMemberService.findByStudygroupMemberId(id);

        if (member == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Convert to DTO
        StudygroupMemberDto memberDto = convertToDto(member);

        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }


    // edit studygroup-member relationship
    @PutMapping("/studygroup-member/{id}")
    public ResponseEntity<String> updateStudygroupMember(@PathVariable Integer id, @RequestBody StudygroupMemberDto updatedMemberDto) {
        StudygroupMember existingMember = studygroupMemberService.findByStudygroupMemberId(id);

        if (existingMember == null) {
            return new ResponseEntity<>("Studygroup member not found", HttpStatus.NOT_FOUND);
        }

        // Update member with new values from DTO
        existingMember.setAccepted(updatedMemberDto.getAccepted());

        // Save the updated member
        studygroupMemberService.updateStudygroupMember(existingMember);

        return new ResponseEntity<>("Studygroup member updated successfully", HttpStatus.OK);
    }

    // delete studygroup-member relationship
    @DeleteMapping("/studygroup-member/{id}")
    public ResponseEntity<String> deleteStudygroupMember(@PathVariable Integer id) {
        StudygroupMember existingMember = studygroupMemberService.findByStudygroupMemberId(id);

        if (existingMember == null) {
            return new ResponseEntity<>("Studygroup member not found", HttpStatus.NOT_FOUND);
        }

        // Delete the member
        studygroupMemberService.deleteStudygroupMemberById(id);

        return new ResponseEntity<>("Studygroup member deleted successfully", HttpStatus.OK);
    }


    // join user into the studygroup
    @PostMapping("/join/study-group/{studygroupId}/member/{userId}")
    public ResponseEntity<String> joinStudygroup(@PathVariable Integer studygroupId, @PathVariable Integer userId) {
        studygroupMemberService.joinStudygroup(studygroupId, userId);
        return new ResponseEntity<>("Joined study group successfully", HttpStatus.OK);
    }

    // find users who are belong to specific studygroup
    @GetMapping("/studygroup-member-list/{studygroupId}")
    public ResponseEntity<List<StudygroupMemberDto>> getMembersByStudygroupId(@PathVariable Integer studygroupId) {
        List<StudygroupMember> members = studygroupMemberService.getMembersByStudygroupId(studygroupId);
        // StudygroupMemberDto 변환 로직을 수행하여 반환
        List<StudygroupMemberDto> memberDtos = convertToDtos(members);
        return new ResponseEntity<>(memberDtos, HttpStatus.OK);
    }

    // delete studygroup-member relationship by studygroupId and userId
    @DeleteMapping("/withdraw/study-group/{studygroupId}/member/{userId}")
    public ResponseEntity<String> withdrawFromStudygroup(@PathVariable Integer studygroupId, @PathVariable Integer userId) {
        studygroupMemberService.withdrawFromStudygroup(studygroupId, userId);
        return new ResponseEntity<>("Withdrawn from study group successfully", HttpStatus.OK);
    }

    // find all studygroups of a specific user
    @GetMapping("/my-group/{userId}")
    public ResponseEntity<List<Studygroup>> getStudygroupsByUserId(@PathVariable Integer userId) {
        List<Studygroup> studygroups = studygroupMemberService.getStudygroupsByUserId(userId);
        return new ResponseEntity<>(studygroups, HttpStatus.OK);
    }

    // convert to dto studygroup-member relationship lists
    private List<StudygroupMemberDto> convertToDtos(List<StudygroupMember> members) {
        return members.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // convert to dto studygroup-member
    private StudygroupMemberDto convertToDto(StudygroupMember studygroupMember) {
        StudygroupMemberDto dto = new StudygroupMemberDto();
        dto.setId(studygroupMember.getId());
        dto.setUserId(studygroupMember.getUser().getId());
        dto.setStudygroupId(studygroupMember.getStudygroup().getId());
        dto.setJoinedDate(studygroupMember.getJoinedDate());
        dto.setAccepted(studygroupMember.getAccepted());
        return dto;
    }

    // accepted condition: If user applied to join studygroup,
    // but not yet approved.
    // find users who are belong to specific studygroup + filter by accepted condition
    @GetMapping("/studygroup-member-list/{studygroupId}/{accepted}")
    public ResponseEntity<List<StudygroupMemberDto>> getMembersByAcceptedAndStudygroupId(@PathVariable Integer studygroupId, @PathVariable Boolean accepted) {
        List<StudygroupMember> members = studygroupMemberService.getMembersByAcceptedAndStudygroupId(accepted, studygroupId);
        // StudygroupMemberDto 변환 로직을 수행하여 반환
        List<StudygroupMemberDto> memberDtos = convertToDtos(members);
        return new ResponseEntity<>(memberDtos, HttpStatus.OK);
    }
    // find all studygroups of a specific user + filter by accepted condition
    @GetMapping("/my-group/{userId}/{accepted}")
    public ResponseEntity<List<Studygroup>> getStudygroupsByAcceptedAndUserId(@PathVariable Integer userId, @PathVariable Boolean accepted) {
        List<Studygroup> studygroups = studygroupMemberService.getStudygroupsByAcceptedAndUserId(accepted, userId);
        return new ResponseEntity<>(studygroups, HttpStatus.OK);
    }

}
