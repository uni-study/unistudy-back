package unistudy.unistudy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/join/study-group/{studygroupId}/member/{userId}")
    public ResponseEntity<String> joinStudygroup(@PathVariable int studygroupId, @PathVariable int userId) {
        studygroupMemberService.joinStudygroup(studygroupId, userId);
        return new ResponseEntity<>("Joined study group successfully", HttpStatus.OK);
    }

    @GetMapping("/studygroup-member/{studygroupId}")
    public ResponseEntity<List<StudygroupMemberDto>> getMembersByStudygroupId(@PathVariable int studygroupId) {
        List<StudygroupMember> members = studygroupMemberService.getMembersByStudygroupId(studygroupId);
        // StudygroupMemberDto 변환 로직을 수행하여 반환
        List<StudygroupMemberDto> memberDtos = convertToDtos(members);
        return new ResponseEntity<>(memberDtos, HttpStatus.OK);
    }

    @DeleteMapping("/withdraw/study-group/{studygroupId}/member/{userId}")
    public ResponseEntity<String> withdrawFromStudygroup(@PathVariable int studygroupId, @PathVariable int userId) {
        studygroupMemberService.withdrawFromStudygroup(studygroupId, userId);
        return new ResponseEntity<>("Withdrawn from study group successfully", HttpStatus.OK);
    }

    @GetMapping("/my-group/{userId}")
    public ResponseEntity<List<Studygroup>> getStudygroupsByUserId(@PathVariable int userId) {
        List<Studygroup> studygroups = studygroupMemberService.getStudygroupsByUserId(userId);
        return new ResponseEntity<>(studygroups, HttpStatus.OK);
    }

    private List<StudygroupMemberDto> convertToDtos(List<StudygroupMember> members) {
        return members.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private StudygroupMemberDto convertToDto(StudygroupMember studygroupMember) {
        StudygroupMemberDto dto = new StudygroupMemberDto();
        dto.setId(studygroupMember.getId());
        dto.setUserId(studygroupMember.getUser().getId());
        dto.setStudygroupId(studygroupMember.getStudygroup().getId());
        dto.setJoinedDate(studygroupMember.getJoinedDate());
        return dto;
    }

}
