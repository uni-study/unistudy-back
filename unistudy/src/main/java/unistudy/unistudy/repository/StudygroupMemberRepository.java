package unistudy.unistudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unistudy.unistudy.domain.StudygroupMember;

import java.util.List;

@Repository
public interface StudygroupMemberRepository extends JpaRepository<StudygroupMember, Integer> {
    List<StudygroupMember> findByUser_id(Integer userId);
    List<StudygroupMember> findByStudygroup_id(Integer studygroupId);
}
