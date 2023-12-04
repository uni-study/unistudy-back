package unistudy.unistudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unistudy.unistudy.domain.Studygroup;
import unistudy.unistudy.domain.StudygroupMember;
import unistudy.unistudy.domain.User;
import unistudy.unistudy.repository.StudygroupMemberRepository;
import unistudy.unistudy.repository.StudygroupRepository;
import unistudy.unistudy.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudygroupMemberService  {

    private final StudygroupMemberRepository studygroupMemberRepository;
    private final StudygroupRepository studygroupRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudygroupMemberService(StudygroupMemberRepository studygroupMemberRepository, StudygroupRepository studygroupRepository, UserRepository userRepository) {
        this.studygroupMemberRepository = studygroupMemberRepository;
        this.studygroupRepository = studygroupRepository;
        this.userRepository = userRepository;
    }

    public StudygroupMember findByStudygroupMemberId(Integer id) {
        return studygroupMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudygroupMember not found with id: " + id));
    }

    @Transactional
    public void updateStudygroupMember(StudygroupMember updatedMember) {
        studygroupMemberRepository.save(updatedMember);
    }

    @Transactional
    public void deleteStudygroupMemberById(Integer id) {
        studygroupMemberRepository.deleteById(id);
    }
    public void joinStudygroup(Integer studygroupId, Integer userId) {
        // 스터디그룹 멤버 추가 로직
        // 중복 가입을 막기 위한 검증 로직 등을 추가할 수 있습니다.

        // 기존의 스터디그룹과 유저를 찾아옴
        Studygroup studygroup = studygroupRepository.findById(studygroupId)
                .orElseThrow(() -> new RuntimeException("Studygroup not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 기존의 유저와 스터디그룹을 사용하여 StudygroupMember 생성
        StudygroupMember studygroupMember = new StudygroupMember();
        studygroupMember.setStudygroup(studygroup);
        studygroupMember.setUser(user);

        studygroupMemberRepository.save(studygroupMember);
    }

    public List<StudygroupMember> getMembersByStudygroupId(Integer studygroupId) {
        // 스터디그룹의 모든 멤버 조회 로직
        return studygroupMemberRepository.findByStudygroupId(studygroupId);
    }
    @Transactional
    public void withdrawFromStudygroup(Integer studygroupId, Integer userId) {
        // 스터디그룹에서 멤버 탈퇴 로직
        studygroupMemberRepository.deleteByStudygroupIdAndUserId(studygroupId, userId);
    }

    public List<Studygroup> getStudygroupsByUserId(Integer userId) {
        // 유저가 속한 모든 스터디그룹 조회 로직
        return studygroupMemberRepository.findStudygroupsByUserId(userId);
    }

    public List<StudygroupMember> getMembersByAcceptedAndStudygroupId(Boolean accepted, Integer studygroupId) {
        // accepted 값과 studygroupId 값에 따라 해당하는 모든 멤버 조회 로직
        return studygroupMemberRepository.findByAcceptedAndStudygroupId(accepted, studygroupId);
    }

    public List<Studygroup> getStudygroupsByAcceptedAndUserId(Boolean accepted, Integer userId) {
        // accepted 값과 userId 값에 따라 해당하는 모든 스터디그룹 조회 로직
        return studygroupMemberRepository.findStudygroupsByAcceptedAndUserId(accepted, userId);
    }


}
