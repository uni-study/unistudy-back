package unistudy.unistudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unistudy.unistudy.domain.Studygroup;
import unistudy.unistudy.domain.StudygroupMember;



import java.util.List;

@Repository
public interface StudygroupMemberRepository extends JpaRepository<StudygroupMember, Integer> {

    // 스터디그룹의 모든 멤버 조회
    @Query("SELECT m FROM StudygroupMember m WHERE m.studygroup.id = :studygroupId")
    List<StudygroupMember> findByStudygroupId(@Param("studygroupId") int studygroupId);

    // 유저가 속한 모든 스터디그룹 조회
    @Query("SELECT m.studygroup FROM StudygroupMember m WHERE m.user.id = :userId")
    List<Studygroup> findStudygroupsByUserId(@Param("userId") int userId);

    // 스터디그룹에서 멤버 탈퇴
    void deleteByStudygroupIdAndUserId(int studygroupId, int userId);
}
