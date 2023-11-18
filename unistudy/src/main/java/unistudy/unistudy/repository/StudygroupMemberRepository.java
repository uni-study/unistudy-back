package unistudy.unistudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unistudy.unistudy.domain.StudygroupMember;

@Repository
public interface StudygroupMemberRepository extends JpaRepository<StudygroupMember, Integer> {
}
